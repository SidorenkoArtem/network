package com.social.network.services;

import com.social.network.configuration.ContextHolder;
import com.social.network.exceptions.ConversationNotExistException;
import com.social.network.exceptions.UserNotConsistInConversationException;
import com.social.network.model.dao.UserConversation;
import com.social.network.repositories.ConversationRepository;

public interface BaseService {
    ConversationRepository userConversationRepository();

    default void checkUserConversation(final Long conversationId) {
        final UserConversation userConversation = userConversationRepository().findById(conversationId).
                orElseThrow(ConversationNotExistException::new);
        final Long userId = ContextHolder.userId();
        if (userConversation.getCreatorUserId() != userId && userConversation.getCompanionUserId() != userId) {
            throw new UserNotConsistInConversationException();
        }
    }
}
