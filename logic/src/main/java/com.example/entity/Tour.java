package com.example.entity;

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

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private String cost;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "tour",fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "tour",fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_company_id")
    private TravelCompany company;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE} ,fetch = FetchType.LAZY)
    @JoinTable(name = "tour_to_type",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "type_tour_id"))
    private Set<TourType> typeSet = new HashSet<>();





}
