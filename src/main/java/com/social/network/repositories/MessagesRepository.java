package com.social.network.repositories;

import com.social.network.model.dao.Messages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    List<Messages> findMessagesByConversationIdEquals(final Long conversationId, final Pageable pageable);
    int countByConversationIdEquals(final Long conversationId);
}
