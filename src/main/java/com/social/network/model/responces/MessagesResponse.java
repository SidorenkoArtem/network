package com.social.network.model.responces;

import com.social.network.model.dto.MessagesDto;
import lombok.Data;
import java.util.List;

@Data
public class MessagesResponse {
    private final List<MessagesDto> lastMessagesDtos;
    private final Integer count;
}
