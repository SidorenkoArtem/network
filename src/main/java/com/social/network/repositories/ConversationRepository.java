package com.social.network.repositories;

import com.social.network.model.dao.UserConversation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<UserConversation, Long> {
    @Query(value = "select conversation from UserConversation conversation " +
            "where conversation.companionUserId = ?1 or conversation.creatorUserId = ?1")
    List<UserConversation> getConversationByUserId(final Long userId, final Pageable pageable);

    @Query(value = "select count(conversation) from UserConversation conversation " +
            "where conversation.companionUserId = ?1 or conversation.creatorUserId = ?1")
    int getConversationByUserIdCount(final Long userId);
}
