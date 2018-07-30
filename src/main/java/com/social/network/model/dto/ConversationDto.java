package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversationDto {
    private Long id;
    private Long userId;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimestamp;
    private Long userIdInterlocutor;
}
