package com.bookbouqet.mapper;

import com.bookbouqet.entity.Book;
import com.bookbouqet.entity.FeedBack;
import com.bookbouqet.response.FeedBackResponse;
import com.bookbouqet.entity.User;
import com.bookbouqet.request.FeedBackRequest;
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
