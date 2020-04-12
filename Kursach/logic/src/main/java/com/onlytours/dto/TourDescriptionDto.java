package com.onlytours.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDescriptionDto extends BaseDto {

    private Long id;
    private String description;
    private int dayNumber;
    private String tourName;
    private Long idTour;

}
