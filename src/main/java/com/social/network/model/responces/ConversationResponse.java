package com.social.network.model.responces;

import com.social.network.model.dto.MessagesDto;
import lombok.Data;

import java.util.List;

@Data
public class ConversationResponse {
    private final List<MessagesDto> commentDtos;
    private final Integer count;
}
