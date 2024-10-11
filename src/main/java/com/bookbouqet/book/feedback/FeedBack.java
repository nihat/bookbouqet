package com.bookbouqet.book.feedback;


import com.bookbouqet.book.book.Book;
import com.bookbouqet.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class FeedBack extends BaseEntity {

    private String title;
    private double note;
    private String comment;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
