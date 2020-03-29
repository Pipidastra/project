package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="reservation")
@Data
public class Reservation extends BaseEntity {

    @Column(name = "number_person")
    private int numberPerson;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
