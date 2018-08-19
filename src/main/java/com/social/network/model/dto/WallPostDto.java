package com.social.network.model.dto;

import lombok.Data;

@Data
public class WallPostDto {
    private Long postId;
    private SimpleUserDto user;
    private String text;
    private String fileUrl;
}
