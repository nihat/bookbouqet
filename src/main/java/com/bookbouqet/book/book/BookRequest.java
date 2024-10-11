package com.bookbouqet.book.book;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record BookRequest(
        String title,
        String author,
        String isbn,
        String synopsis,
        String bookCover
) {


}
