package com.social.network.model.dto;

import lombok.Data;

@Data
public class SimpleSocialGroupDto {
    private Long id;
    private Long userId;
    private String name;
    private String imageUrl;
    private String description;
}