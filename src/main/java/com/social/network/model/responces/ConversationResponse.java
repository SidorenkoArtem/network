package com.social.network.model.responces;

import com.social.network.model.dto.ConversationDto;
import lombok.Data;
import java.util.List;

@Data
public class ConversationResponse {
    private final List<ConversationDto> conversations;
    private final Integer count;
}
