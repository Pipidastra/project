package com.onlytours.dto;

import com.onlytours.entity.Comment;
import com.onlytours.entity.Reservation;
import com.onlytours.entity.TourDescription;
import com.onlytours.entity.TravelCompany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TourDto extends BaseDto{

    private Long id;
    private String name;
    private String country;
    private Date exitDate;
    private String numberDays;
    private String image;
    private Set<TourDescriptionDto> description;
    private String cost;
    private String type;
    private int rating;
    private Set<ReservationDto> reservations = new HashSet<>();
    private Set<CommentDto> comments = new HashSet<>();
    private Long companyId;


}
