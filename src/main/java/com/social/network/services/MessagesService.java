package com.social.network.services;

import com.social.network.exceptions.ConversationNotExistException;
import com.social.network.model.dao.Conversation;
import com.social.network.model.dao.Messages;
import com.social.network.model.dto.ConversationDto;
import com.social.network.model.dto.MessagesDto;
import com.social.network.model.requests.MessageRequest;
import com.social.network.model.responces.ConversationResponse;
import com.social.network.model.responces.ConversationsResponse;
import com.social.network.model.responces.MessagesResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    public MessagesResponse getMessages(final Long userId ,final Integer offset, final Integer limit) {
        final Long currentUserId = 0L;//Todo
        final Conversation conversation = conversationRepository.getUserConversation(currentUserId, userId)
                .orElseThrow(ConversationNotExistException::new);
        final List<Messages> messages = messagesRepository
                .findMessagesByConversationIdEquals(conversation.getId(), PageRequest.of(offset, limit));
        final List<MessagesDto> messagesDtos = messages.stream().map(ConvertUtil::convertToMessagesDto).collect(Collectors.toList());
        final Integer count = messagesRepository.countByConversationIdEquals(conversation.getId());
        return new MessagesResponse(messagesDtos, count);
    }

    public ConversationResponse getMessagesByConversation(final Long conversationId, final Integer offset, final Integer limit) {
        final List<Messages> messages = messagesRepository.findMessagesByConversationIdEquals(conversationId, PageRequest.of(offset, limit));
        final Integer count = messagesRepository.countByConversationIdEquals(conversationId);
        final List<MessagesDto> messagesDtos = messages.stream().map(ConvertUtil::convertToMessagesDto).collect(Collectors.toList());
        return new ConversationResponse(messagesDtos, count);
    }

    public void addMessagesToConversation(final MessageRequest messageRequest, final Long conversationId) {
        final Long userId = 0L;//Todo
        final Messages message = new Messages();
        message.setUserId(userId);
        message.setConversationId(conversationId);
        message.setText(messageRequest.getText());
        message.setFileUrl(messageRequest.getFileUrl());
        messagesRepository.save(message);
    }
}
