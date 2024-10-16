package com.bookbouqet.book.book;

import com.bookbouqet.book.BookMapper;
import com.bookbouqet.book.exception.OperationNotPermittedException;
import com.bookbouqet.book.history.BookTransactionHistory;
import com.bookbouqet.book.history.BookTransactionHistoryMapper;
import com.bookbouqet.book.history.BookTransactionHistoryRepository;
import com.bookbouqet.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookTransactionHistoryRepository transactionHistoryRepository;
    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final FileStorageService fileStorageService;


    public Integer saveBook(BookRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Book book = bookMapper.toBook(request);
        book.setOwner(user);
        bookRepository.save(book);
        return book.getId();
    }


    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
    }

    public PageResponse<BookResponse> findAllBooks(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> bookPage = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookResponse> books = bookPage.stream().map(bookMapper::toBookResponse).toList();
        return new PageResponse<BookResponse>(
                books,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalPages(),
                bookPage.getTotalElements(),
                bookPage.isLast(),
                bookPage.isFirst()
        );

    }

    public PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> bookPage = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

        List<BookResponse> books = bookPage.stream().map(bookMapper::toBookResponse).toList();
        return new PageResponse<BookResponse>(
                books,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalPages(),
                bookPage.getTotalElements(),
                bookPage.isLast(),
                bookPage.isFirst()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<BookTransactionHistory> allBorrowedBooks = transactionHistoryRepository.findAllBorrowedBooks(pageable, user.getId());
        List<BorrowedBookResponse> borrowedBooks = allBorrowedBooks.stream().map(bookMapper::toBorrowedBookResponse).toList();
        return new PageResponse<BorrowedBookResponse>(
                borrowedBooks,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.isLast(),
                allBorrowedBooks.isFirst()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<BookTransactionHistory> allBorrowedBooks = transactionHistoryRepository.findAllReturnedBooks(pageable, user.getId());
        List<BorrowedBookResponse> borrowedBooks = allBorrowedBooks.stream().map(bookMapper::toBorrowedBookResponse).toList();
        return new PageResponse<BorrowedBookResponse>(
                borrowedBooks,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.isLast(),
                allBorrowedBooks.isFirst()
        );
    }


    public Integer updateShareableStatus(Integer bookId, Authentication authentication) throws OperationNotPermittedException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot update shareable status while you are not owner of the book");
        }
        book.setShareable(!book.isShareable());
        bookRepository.save(book);
        return bookId;
    }

    public Integer updateArchivedStatus(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot update archived status while you are not owner of the book");
        }
        book.setShareable(!book.isArchived());
        bookRepository.save(book);
        return bookId;
    }

    public Integer borrowBook(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();
        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot borrow the book since your own book !");
        }
        if (!book.isShareable() || book.isArchived()) {
            throw new OperationNotPermittedException("You cannot borrow the book since book is not shareable or archived !");
        }
        boolean isAlreadyBorrowed = bookTransactionHistoryRepository.isAlreadyBorrowed(bookId);
        if (isAlreadyBorrowed) {
            throw new OperationNotPermittedException("Requested book is already borrowed !");
        }

        BookTransactionHistory bookTransactionHistory = BookTransactionHistoryMapper.toBookTransaction(book, user);
        transactionHistoryRepository.save(bookTransactionHistory);

        book.setShareable(false);
        bookRepository.save(book);
        return bookId;
    }

    public Integer returnBook(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();

        BookTransactionHistory transactionHistory = transactionHistoryRepository
                .findByBookId(bookId, user.getId()).orElseThrow(() -> new EntityNotFoundException("Book transaction not found "));

        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot return your own book !");
        }
        if (!Objects.equals(user.getId(), transactionHistory.getUser().getId())) {
            throw new OperationNotPermittedException("You cannot return book since you have not borrowed it !");
        }
        transactionHistory.setReturned(true);
        transactionHistory.setLastModifiedBy(user.getId());
        transactionHistory.setLastModifiedDate(LocalDate.now());
        transactionHistoryRepository.save(transactionHistory);
        return bookId;
    }

    public Integer approveReturn(Integer bookId, Authentication authentication) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();

        BookTransactionHistory transactionHistory = transactionHistoryRepository
                .findBorrowedBookByOwnerId(bookId, user.getId()).orElseThrow(() -> new EntityNotFoundException("Book is not returned yet "));

        if (!Objects.equals(user.getId(), transactionHistory.getBook().getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot approve the book since you have not owner of it !");
        }

        transactionHistory.setReturnApproved(true);
        transactionHistory.setLastModifiedBy(user.getId());
        transactionHistory.setLastModifiedDate(LocalDate.now());
        transactionHistoryRepository.save(transactionHistory);

        return book.getId();
    }

    public void uploadCoverPicture(MultipartFile file, Authentication authentication, Integer bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by id " + bookId));
        User user = (User) authentication.getPrincipal();

        var cover = fileStorageService.saveFile(file, user.getId());

        book.setBookCover(cover);
        bookRepository.save(book);
    }
}
