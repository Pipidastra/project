package com.onlytours.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="reservation")
@Getter
@Setter
public class Reservation extends BaseEntity {

    @Column(name = "number_person")
    private int numberPerson;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
