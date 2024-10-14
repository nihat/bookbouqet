package com.bookbouqet.book.book;

import lombok.Builder;

@Builder
public record BookResponse(
        Integer id,
        String title,
        String author,
        String isbn,
        String synopsis,
        String owner ,
        double note,
        boolean archived,
        boolean shareable,
        byte[] bookCover
){
}
