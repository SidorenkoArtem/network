package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiftDto {
    private Long id;
    private String imageUrl;
    private LocalDateTime createTimestamp;
}
