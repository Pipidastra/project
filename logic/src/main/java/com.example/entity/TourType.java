package com.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="type_tour")
@Getter
@Setter
public class TourType extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "tour_to_type",
            joinColumns = @JoinColumn(name = "type_tour_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private Set<Tour> tourSet = new HashSet<>();
}
