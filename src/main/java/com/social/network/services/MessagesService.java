package com.social.network.services;

import com.social.network.configuration.ContextHolder;
import com.social.network.controllers.BaseMvcController;
import com.social.network.exceptions.ConversationNotExistsException;
import com.social.network.exceptions.UserNotExistsException;
import com.social.network.model.dao.Messages;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserConversation;
import com.social.network.model.requests.MessageRequest;
import com.social.network.model.responces.ConversationResponse;
import com.social.network.model.responces.MessageResponse;
import com.social.network.model.responces.MessagesResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import static com.social.network.ApplicationConstants.*;

@Service
@RequiredArgsConstructor
public class MessagesService implements BaseService {

    private final MessagesRepository messagesRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationResponse getConversations(final Integer page, final Integer limit) {
        final Long userId = ContextHolder.userId();
        final List<UserConversation> userConversations = conversationRepository.getConversationByUserId(userId,
                PageRequest.of(page, limit, Sort.by(Sort.Order.desc(UPDATE_TIMESTAMP))));

        final Set<Long> userCreatorIds = userConversations.stream().map(UserConversation::getCreatorUserId).collect(Collectors.toSet());
        final Set<Long> userCompanionIds = userConversations.stream().map(UserConversation::getCompanionUserId).collect(Collectors.toSet());
        final Map<Long, User> creators = userRepository.findUsersByIdIn(userCreatorIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));
        final Map<Long, User> companions = userRepository.findUsersByIdIn(userCompanionIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        final Integer countConversation = conversationRepository.getConversationByUserIdCount(userId);

        return new ConversationResponse(userConversations.stream().map(e -> {
            final Long userCreatorId = e.getCreatorUserId();
            final Long userCompanionId = e.getCompanionUserId();
            return ConvertUtil.convertToConversationDto(e, creators.getOrDefault(userCreatorId, new User()),
                    companions.getOrDefault(userCompanionId, new User()));
        }).collect(Collectors.toList()), countConversation);
    }

    @Transactional
    public MessageResponse sendMessage(final MessageRequest messageRequest) {
        final Long userId = ContextHolder.userId();
        UserConversation userConversation = conversationRepository.getConversationByMember(userId, messageRequest.getReceiverUserId())
                .orElse(null);
        if (userConversation == null) {
            userConversation = newUserConversation(userId, messageRequest.getReceiverUserId());
        }
        userConversation.setUpdateTimestamp(LocalDateTime.now());
        conversationRepository.save(userConversation);
        final Messages message = new Messages();
        message.setUserId(userId);
        message.setConversationId(userConversation.getId());
        message.setText(messageRequest.getText());
        message.setFileUrl(messageRequest.getFileUrl());
        message.setCreateTimestamp(LocalDateTime.now());
        messagesRepository.save(message);
        final User user = userRepository.findById(message.getUserId()).orElseThrow(UserNotExistsException::new);
        return new MessageResponse(ConvertUtil.convertToMessagesDto(message, user));
    }

    public MessagesResponse getConversationMessages(final Long conversationId, final Integer page, final Integer limit) {
        checkUserConversation(conversationId);
        final List<Messages> messages = messagesRepository.findMessagesByConversationIdEquals(conversationId,
                PageRequest.of(page, limit, Sort.by(Sort.Order.desc(CREATE_TIMESTAMP))));
        final Set<Long> messagesUserId = messages.stream().map(Messages::getUserId).collect(Collectors.toSet());
        final Map<Long, User> userIdAndUserMap = userRepository.findUsersByIdIn(messagesUserId).stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        final Integer countMessages = messagesRepository.countMessagesByConversationIdEquals(conversationId);
        return new MessagesResponse(messages.stream().map(e -> {
            final Long userId = e.getUserId();
            return ConvertUtil.convertToMessagesDto(e, userIdAndUserMap.getOrDefault(userId, new User()));
        }).collect(Collectors.toList()), countMessages);
    }

    private UserConversation newUserConversation(final Long creatorUserId, final Long companionUserId) {
        final UserConversation conversation = new UserConversation();
        conversation.setCreatorUserId(creatorUserId);
        conversation.setCompanionUserId(companionUserId);
        conversation.setCreateTimestamp(LocalDateTime.now());
        conversationRepository.save(conversation);
        return conversation;
    }

    public Long getReceiverUserIdByConversation(final Long conversationId) {
        final UserConversation userConversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotExistsException::new);
        if (!userConversation.getCreatorUserId().equals(ContextHolder.userId())) {
            return userConversation.getCreatorUserId();
        } else {
            return userConversation.getCompanionUserId();
        }
    }

    @Override
    public ConversationRepository userConversationRepository() {
        return conversationRepository;
    }
}