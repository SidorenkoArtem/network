package com.social.network.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private final Long id;
    private final Long postId;
    private final String text;
    private final String fileUrl;
    private final LocalDateTime createTimestamp;
}
