package com.onlytours.dto;

import com.onlytours.entity.Comment;
import com.onlytours.entity.Reservation;
import com.onlytours.entity.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto extends BaseDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String role;
    private Set<Role> roleSet;
    private Set<Comment> comments;
    private Set<Reservation> reservations;

}
