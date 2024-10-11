package com.bookbouqet.book.book;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public record BookService(BookRepository bookRepository) {


    public Integer saveBook(BookRequest request, Authentication authentication) {

        Book book = Book.builder()
                        .title(request.title())
                build();

        return book.getId();
    }
}
