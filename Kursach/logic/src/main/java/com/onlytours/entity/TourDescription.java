package com.onlytours.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "tour_description")
@Getter
@Setter
public class TourDescription extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "day_number")
    private int dayNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tour")
    private Tour tour;

}
