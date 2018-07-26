package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagesDto {
    private Long id;
    private Long userId;
    private Long conversationId;
    private String text;
    private String fileUrl;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimestamp;
}
