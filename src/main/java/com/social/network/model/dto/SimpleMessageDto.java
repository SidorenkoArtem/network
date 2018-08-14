package com.social.network.model.dto;

import lombok.Data;

@Data
public class SimpleMessageDto {
    private final SimpleUserDto creatorUser;
    private final SimpleUserDto receiverUser;
}
