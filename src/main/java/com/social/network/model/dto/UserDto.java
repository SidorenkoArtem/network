package com.social.network.model.dto;

import com.social.network.model.enums.Roles;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private Boolean validated;
    private String photoUrl;
    private Roles role;
    private String firstName;
    private LocalDateTime createTimestamp;
}
