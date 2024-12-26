package com.bookbouqet.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

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
