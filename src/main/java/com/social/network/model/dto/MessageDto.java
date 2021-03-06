package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private SimpleUserDto user;
    private Long conversationId;
    private String text;
    private String fileUrl;
    private LocalDateTime createTimestamp;
    private LocalDateTime updateTimestamp;
}
