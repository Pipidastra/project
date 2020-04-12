package com.onlytours.dto;

import lombok.Data;

@Data
public class ReservationDto extends BaseDto {

    private Long id;
    private int numberPerson;
    private Long tourId;
    private Long accountId;
    private String nameTour;
    private String username;
    private String phone;

}
