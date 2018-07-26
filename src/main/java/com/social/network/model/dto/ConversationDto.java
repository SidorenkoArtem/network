package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversationDto {
    private Long id;
    private Long userId;
    private MessagesDto lastMessages;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimestamp;
}
