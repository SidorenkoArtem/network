package com.social.network.repositories;

import com.social.network.model.dao.Messages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<Messages, Long> {

    List<Messages> findMessagesByConversationIdEquals(final Long conversationId, final Pageable pageable);
    int countMessagesByConversationIdEquals(final Long conversationId);

}
