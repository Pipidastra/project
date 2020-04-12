package com.onlytours.dto;


import lombok.Data;

@Data
public class CommentDto extends BaseDto {

    private Long id;
    private String message;
    private int rating;
    private String username;
    private String time;
    private Long tourId;
    private Long accountId;
}
