package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment extends BaseEntity {

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private int rating;

    @Column(name = "tour_id")
    private Long tourId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", updatable = false,insertable = false)
    private Tour tour;

}
