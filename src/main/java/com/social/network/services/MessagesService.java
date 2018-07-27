package com.social.network.services;


import com.social.network.model.responces.ConversationsResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationRepository conversationRepository;

    public ConversationsResponse getUserSimpleConversation(final Long userId, final Integer offset, final Integer limit) {
        final List<Long> conversationIds =

        return new ConversationsResponse(null, null);
    }

    public List<Long> getLastConveration() {
        return null;
    }
}
