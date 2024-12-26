package com.bookbouqet.repository;

import com.bookbouqet.entity.FeedBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {

    @Query("SELECT feedback FROM FeedBack  feedback where feedback.book.id = :bookId")
    Page<FeedBack> findAllFeedBacksByBookId(Pageable pageable, Integer bookId);
}
