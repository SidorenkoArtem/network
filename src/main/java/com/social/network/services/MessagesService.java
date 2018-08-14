package com.social.network.services;

import com.social.network.configuration.ContextHolder;
import com.social.network.model.dao.Messages;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserConversation;
import com.social.network.model.requests.MessageRequest;
import com.social.network.model.responces.ConversationResponse;
import com.social.network.repositories.ConversationRepository;
import com.social.network.repositories.MessagesRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

   public ConversationResponse getConversations(final Integer page, final Integer limit) {
      final Long userId = ContextHolder.userId();
      final List<UserConversation> userConversations = conversationRepository.getConversationByUserId(userId,
             PageRequest.of(page, limit, Sort.by(Sort.Order.desc("updateTimestamp"))));
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

   private UserConversation newUserConversation(final Long creatorUserId, final Long companionUserId) {
       final UserConversation conversation = new UserConversation();
       conversation.setCreatorUserId(creatorUserId);
       conversation.setCompanionUserId(companionUserId);
       conversation.setCreateTimestamp(LocalDateTime.now());
       conversationRepository.save(conversation);
       return conversation;
   }

   @Transactional
    public void sendMessage(final MessageRequest messageRequest) {
        final Long userId = ContextHolder.userId();
        final UserConversation userConversation = conversationRepository.getConversationByMember(userId, messageRequest.getReceiverUserId())
                .orElse(newUserConversation(userId, messageRequest.getReceiverUserId()));
        userConversation.setUpdateTimestamp(LocalDateTime.now());
        conversationRepository.save(userConversation);
        final Messages message = new Messages();
        message.setUserId(userId);
        message.setConversationId(userConversation.getId());
        message.setText(messageRequest.getText());
        message.setFileUrl(messageRequest.getFileUrl());
        message.setCreateTimestamp(LocalDateTime.now());
        messagesRepository.save(message);
    }
}