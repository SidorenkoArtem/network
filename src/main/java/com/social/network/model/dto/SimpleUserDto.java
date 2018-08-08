package com.social.network.model.dto;

import lombok.Data;

@Data
public class SimpleUserDto {
    private final Long userId;
    private final String firstName;
    private final String name;
    private final String photoUrl;
}
