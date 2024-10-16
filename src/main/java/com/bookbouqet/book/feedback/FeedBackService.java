package com.bookbouqet.book.feedback;

import com.bookbouqet.book.book.Book;
import com.bookbouqet.book.book.BookRepository;
import com.bookbouqet.book.book.PageResponse;
import com.bookbouqet.book.exception.OperationNotPermittedException;
import com.bookbouqet.book.history.BookTransactionHistory;
import com.bookbouqet.book.history.BookTransactionHistoryRepository;
import com.bookbouqet.user.User;
import com.bookbouqet.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final FeedBackRepository feedBackRepository;
    private final BookTransactionHistoryRepository transactionHistoryRepository;
    private final FeedBackMapper feedBackMapper;


    Integer save(FeedBackRequest request, Authentication authentication) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        User user = userRepository.findById(((User) authentication.getPrincipal()).getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        BookTransactionHistory transactionHistory = transactionHistoryRepository
                .findByBookId(request.bookId(), user.getId()).orElseThrow(() -> new EntityNotFoundException("Cannot find any book transaction history"));

        if (Objects.equals(user.getId(), book.getOwner().getId())) {
            throw new OperationNotPermittedException("You cannot feedback your own book !");
        }
        if (!Objects.equals(user.getId(), transactionHistory.getUser().getId())) {
            throw new OperationNotPermittedException("You cannot feedback the book since you have not borrowed it !");
        }
        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getId();
    }

    public PageResponse<FeedBackResponse> findAllFeedBacksByBookId(Integer bookId, int page, int size, Authentication authentication) {
        User connectedUser = (User) authentication.getPrincipal();
        User user = userRepository.findById(connectedUser.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<FeedBack> allFeedBacks = feedBackRepository.findAllFeedBacksByBookId(pageable, bookId);
        List<FeedBackResponse> feedBacks = allFeedBacks.stream().map((f) -> feedBackMapper.toFeedBackResponse(f, user)).toList();
        return new PageResponse<FeedBackResponse>(
                feedBacks,
                allFeedBacks.getNumber(),
                allFeedBacks.getSize(),
                allFeedBacks.getTotalPages(),
                allFeedBacks.getTotalElements(),
                allFeedBacks.isLast(),
                allFeedBacks.isFirst()
        );

    }
}
