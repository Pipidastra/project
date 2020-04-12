package com.onlytours.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tour")
@Getter
@Setter
public class Tour extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "exit_date")
    private Date exitDate;

    @Column(name = "number_days")
    private String numberDays;

    @Column(name = "image_url")
    private String image;

    @Column(name = "cost")
    private String cost;

    @Column(name = "type")
    private String type;

    @Column(name = "rating")
    private int rating;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "tour",fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "tour",fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "tour",fetch = FetchType.LAZY)
    private Set<TourDescription> tourDescriptions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_company_id")
    private Account company;

}
