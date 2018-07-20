package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserGiftDto {
    private final Long id;
    private final Long userId;
    private final Long giftId;
    private final LocalDateTime createTimestamp;
}
