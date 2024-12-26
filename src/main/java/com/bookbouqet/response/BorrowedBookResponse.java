package com.bookbouqet.response;

import lombok.Builder;

@Builder
public record BorrowedBookResponse(
        String title,
        String author,
        String isbn,
        boolean returned,
        boolean returnApproved

) {
}
