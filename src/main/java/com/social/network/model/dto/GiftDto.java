package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GiftDto {
    private final Long id;
    private final String imageUrl;
    private final LocalDateTime createTimestamp;
}
