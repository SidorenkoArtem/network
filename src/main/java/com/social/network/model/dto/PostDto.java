package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private final Long id;
    private final Long userId;
    private final Long groupId;
    private final String text;
    private final String fileUrl;
    private final LocalDateTime createTimestamp;
}
