package com.bookbouqet.book.feedback;

import com.bookbouqet.user.User;
import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record FeedBackRequest(
        @NotNull(message = "200")
        @Positive(message = "201")
        @Min(value = 0, message = "202")
        @Max(value = 5, message = "2003")
        Double note,

        @NotBlank(message = "204")
        String title,

        @NotBlank(message = "205")
        String comment,

        @NotNull(message = "205")
        Integer bookId,

        @NotNull
        User user
) {
}
