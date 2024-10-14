package com.bookbouqet.book.history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("SELECT history from BookTransactionHistory  history where history.user.id = :userId")
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable pageable, Integer userId);

    @Query("SELECT history from BookTransactionHistory  history where history.book.owner.id = :userId and history.returned = true")
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable pageable, Integer id);

    @Query("SELECT COUNT(*) AS isAlreadyBorrowed FROM BookTransactionHistory history " +
            " WHERE history.book.id =:bookId AND  history.returnApproved = false ")
    boolean isAlreadyBorrowed(Integer bookId, Integer userId);

    @Query("SELECT history FROM BookTransactionHistory history WHERE history.book.id = :bokId " +
            "AND history.user.id != :userId  AND history.returned = false AND history.returnApproved = false ")
    Optional<BookTransactionHistory> findByBookId(Integer bookId,Integer userId);

    @Query("SELECT history FROM BookTransactionHistory history WHERE history.book.id = :bokId " +
            "AND history.book.owner.id = :userId  AND history.returned = true AND history.returnApproved = false ")
    Optional<BookTransactionHistory> findBorrowedBookByOwnerId(Integer bookId, Integer userId);
}
