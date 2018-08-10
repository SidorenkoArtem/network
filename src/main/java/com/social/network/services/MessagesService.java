package com.social.network.services;

import com.social.network.model.dao.Conversation;
import com.social.network.model.dao.Messages;
import com.social.network.model.dto.ConversationDto;
import com.social.network.model.dto.MessagesDto;
import com.social.network.model.requests.MessageRequest;
import com.social.network.model.responces.ConversationsResponse;
import com.social.network.model.responces.MessagesResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationRepository conversationRepository;

    public ConversationsResponse getConversations(final Integer offset, final Integer limit) {
        final Long currentUserId = 0L;//Todo
        final List<Conversation> conversations = conversationRepository
                .findConversationsByUserIdEqualsOrUserIdInterlocutorEquals(currentUserId, currentUserId, PageRequest.of(offset, limit));
        final List<ConversationDto> conversationDtos = conversations.stream()
                .map(ConvertUtil::convertToConversationDto).collect(Collectors.toList());
        final Integer count = conversationRepository.countByUserIdEqualsOrUserIdInterlocutorEquals(currentUserId, currentUserId);
        return new ConversationsResponse(conversationDtos, count);
    }

    public MessagesResponse getMessages(final Long userId ,final Integer page, final Integer limit) {
        final Long currentUserId = 6L;//TODO get userId from context holder
        final List<Messages> messages = messagesRepository.getUserMessages(currentUserId, userId, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final List<MessagesDto> messageDtos = messages.stream().map(ConvertUtil::convertToMessagesDto).collect(Collectors.toList());
        return new MessagesResponse(messageDtos, messagesRepository.getCountMessages(currentUserId, userId));
    }

    public void sendMessage(final MessageRequest messageRequest) {
        final Long userId = 6L;
        final Messages message = new Messages();
        message.setUserId(userId);
        message.setReceiverUserId(messageRequest.getReceiverUserId());
        message.setText(messageRequest.getText());
        message.setFileUrl(messageRequest.getFileUrl());
        messagesRepository.save(message);
    }
}
