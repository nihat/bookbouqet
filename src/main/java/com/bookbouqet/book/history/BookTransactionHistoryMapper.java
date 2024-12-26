package com.bookbouqet.book.history;

import com.bookbouqet.entity.Book;
import com.bookbouqet.entity.User;

import java.time.LocalDate;

public class BookTransactionHistoryMapper {

    public static BookTransactionHistory toBookTransaction(Book book, User user) {
        return BookTransactionHistory.builder()
                .book(book)
                .lastModifiedBy(user.getId())
                .lastModifiedDate(LocalDate.now())
                .returned(false)
                .returnApproved(false)
                .user(user)
                .build();
    }
}
