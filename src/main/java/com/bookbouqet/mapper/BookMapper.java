package com.bookbouqet.mapper;

import com.bookbouqet.entity.Book;
import com.bookbouqet.request.BookRequest;
import com.bookbouqet.response.BookResponse;
import com.bookbouqet.response.BorrowedBookResponse;
import com.bookbouqet.book.common.FileUtils;
import com.bookbouqet.book.history.BookTransactionHistory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BookMapper {

    public Book toBook(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .bookCover(request.bookCover())
                .author(request.author())
                .isbn(request.isbn())
                .synopsis(request.synopsis())
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .bookCover(book.getBookCover().getBytes(StandardCharsets.UTF_8))
                .author(book.getBookCover())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .shareable(book.isShareable())
                .archived(book.isArchived())
                .bookCover(FileUtils.copyFileFromDir(book.getBookCover()))
                .build();
    }

    public BookResponse mapToBookTransactionHistResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .bookCover(book.getBookCover().getBytes(StandardCharsets.UTF_8))
                .author(book.getBookCover())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .shareable(book.isShareable())
                .archived(book.isArchived())
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory bookTransactionHistory) {
        return BorrowedBookResponse.builder()
                .title(bookTransactionHistory.getBook().getTitle())
                .author(bookTransactionHistory.getBook().getBookCover())
                .isbn(bookTransactionHistory.getBook().getIsbn())
                .returned(bookTransactionHistory.isReturned())
                .returnApproved(bookTransactionHistory.isReturnApproved())
                .build();
    }
}
