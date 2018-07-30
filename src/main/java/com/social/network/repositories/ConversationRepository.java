package com.social.network.repositories;

import com.social.network.model.dao.Conversation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
    @Query("Select conversation from Conversation conversation where (conversation.userId = ?1 and " +
    "conversation.userIdInterlocutor = ?2) or (conversation.userId = ?2 and conversation.userIdInterlocutor = ?1)")
    Optional<Conversation> getUserConversation(final Long currentUserId, final Long userId);

    List<Conversation> findConversationsByUserIdEqualsOrUserIdInterlocutorEquals(final Long userId, final Long userIdd, final Pageable pageable);

    int countByUserIdEqualsOrUserIdInterlocutorEquals(final Long userId, final Long userIdd);
}
