package com.social.network.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserGiftDto {
    private Long id;
    private SimpleUserDto user;
    private Long giftUserFromId;
    private GiftDto gift;
    private LocalDateTime createTimestamp;
}
