package com.social.network.repositories;

import com.social.network.model.dao.UserConversation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends CrudRepository<UserConversation, Long> {
    @Query(value = "select conversation from UserConversation conversation " +
            "where conversation.companionUserId = ?1 or conversation.creatorUserId = ?1")
    List<UserConversation> getConversationByUserId(final Long userId, final Pageable pageable);

    @Query(value = "select count(conversation) from UserConversation conversation " +
            "where conversation.companionUserId = ?1 or conversation.creatorUserId = ?1")
    int getConversationByUserIdCount(final Long userId);

    @Query(value = "select conversation from UserConversation  conversation where (conversation.creatorUserId = ?1 and" +
            " conversation.companionUserId = ?2) or (conversation.creatorUserId = ?2 and conversation.companionUserId = ?1)")
    Optional<UserConversation> getConversationByMember(final Long creatorUserId, final Long companionUserId);
}
