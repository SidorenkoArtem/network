package com.social.network.repositories;

import com.social.network.model.dao.Messages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    //@Query("select messages from Messages messages ") TOdo
    List<Messages> getLastMessagesByConversationId(final List<Long> conversation);
}
