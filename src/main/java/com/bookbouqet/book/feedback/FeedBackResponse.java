package com.bookbouqet.book.feedback;

import com.bookbouqet.user.User;
import lombok.Builder;

@Builder
public record FeedBackResponse(
        String title,
        double note,
        String comment,
        boolean isOwnFeedBack,
        User user
) {
}
