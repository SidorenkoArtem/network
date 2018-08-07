package com.social.network.model.dto;

import com.social.network.model.dao.Gift;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserGiftDto {
    private Long id;
    private Long userId;
    private Long giftUserFromId;
    private GiftDto gift;
    private LocalDateTime createTimestamp;
}
