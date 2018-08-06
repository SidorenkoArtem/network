package com.social.network.model.requests;

import com.social.network.model.enums.Roles;
import com.social.network.model.enums.Sex;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private final String login;
    private final String password;
    private final String firstName;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final Sex sex;
    private final String email;
    private final String photoUrl;
    private final Roles role;
    private final Boolean showWall;
    private final Boolean showLocation;
    private final Boolean showGift;
}
