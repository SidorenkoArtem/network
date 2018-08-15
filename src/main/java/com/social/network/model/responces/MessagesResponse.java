package com.social.network.model.responces;

import com.social.network.model.dto.MessageDto;
import lombok.Data;
import java.util.List;

@Data
public class MessagesResponse {
    private final List<MessageDto> lastMessageDtos;
    private final Integer count;
}
