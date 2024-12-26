package com.bookbouqet.response;

import com.bookbouqet.entity.User;
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
