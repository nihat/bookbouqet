package com.bookbouqet.book.book;

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
