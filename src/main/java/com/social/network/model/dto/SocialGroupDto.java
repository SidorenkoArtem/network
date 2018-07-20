package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SocialGroupDto {
    private Long id;
    private String name;
    private String header;
    private Long userId;
    private String imageUrl;
    private String description;
    private Boolean hideReaders;
    private Boolean privateSocialGroup;
    private LocalDateTime createTimestamp;
}
