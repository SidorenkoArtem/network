package com.social.network.repositories;

import com.social.network.model.dao.Conversation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    @Query("select distinct conversation.id from Conversation conversation inner join Messages messages on messages.conversationId = conversation.id "
    + "where messages.userId = ?1 order by messages.createTimestamp.asc")//TODO
    List<Conversation> getLastsConversation(final Long userId, final Integer offset, final Integer limit);
}
