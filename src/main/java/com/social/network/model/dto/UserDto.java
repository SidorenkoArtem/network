package com.social.network.model.dto;

import com.social.network.model.enums.Roles;
import com.social.network.model.enums.Sex;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Sex sex;
    private String email;
    private String photoUrl;
    private Roles role;

    private Boolean active;
    private Boolean validated;

    private Boolean showWall;
    private Boolean showLocation;
    private Boolean showGift;

    private LocalDateTime createTimestamp;
}
