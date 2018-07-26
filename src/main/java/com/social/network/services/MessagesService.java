package com.social.network.services;

import com.social.network.model.dao.Conversation;
import com.social.network.model.dao.Messages;
import com.social.network.model.responces.ConversationsResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationRepository conversationRepository;

    public ConversationsResponse getUserSimpleConversation(final Integer offset, final Integer limit) {
        final Long userId = 0L;//TODO
        final List<Conversation> lastConversations = conversationRepository.getLastsConversation(userId, offset, limit);
        final List<Long> lastConversationIds = lastConversations.stream().map(Conversation::getId).collect(Collectors.toList());
        final Map<Long, Messages> lastMessages = messagesRepository.getLastMessagesByConversationId(lastConversationIds)
                .stream().collect(Collectors.toMap(Messages::getConversationId, Function.identity()));
//TODO DodelatbI
        return new ConversationsResponse(null, null);
    }
}
