package com.bookbouqet.entity;

import com.bookbouqet.book.history.BookTransactionHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity {

    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean shareable;
    private boolean archived;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> bookTransactionHistories;


    @Transient
    public double getRate(){
        double rate = 0.0;
        if(!CollectionUtils.isEmpty(feedBacks)){
            rate = feedBacks.stream().mapToDouble(FeedBack::getNote).average().orElse(0.0);
        }
        return Math.round(rate * 10.0) / 10.0;
    }


}
