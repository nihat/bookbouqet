package com.bookbouqet.book.book;

import com.bookbouqet.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerId(Integer ownerId) {
        return (root, query, cb) -> cb.equal(root.get("ownerId"), ownerId);
    }
}
