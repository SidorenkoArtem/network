package com.social.network.model.dto;

import lombok.Data;

@Data
public class ConversationDto {
    private Long id;
    private SimpleUserDto creatorConversation;
    private SimpleUserDto companionConversation;
}
