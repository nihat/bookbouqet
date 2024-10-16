package com.bookbouqet.book.feedback;

import com.bookbouqet.book.book.Book;
import com.bookbouqet.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
@AllArgsConstructor
public class FeedBackMapper {

    public FeedBack toFeedBack(FeedBackRequest request) {
        return FeedBack.builder()
                .book(Book.builder().id(request.bookId()).build())
                .title(request.title())
                .note(request.note())
                .comment(request.comment())
                .createdBy(request.user() != null ? request.user().getId() : null)
                .build();
    }


    public FeedBackResponse toFeedBackResponse(FeedBack feedBack, User user) {
        return FeedBackResponse.builder()
                .user(user)
                .comment(feedBack.getComment())
                .note(feedBack.getNote())
                .title(feedBack.getTitle())
                .isOwnFeedBack(ObjectUtils.nullSafeEquals(user.getId() , feedBack.getCreatedBy()))
                .build();
    }
}
