package com.social.network.repositories;

import com.social.network.model.dao.Messages;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MessagesRepository extends CrudRepository<Messages, Long> {
    @Query(value = "select message from Messages message where (message.userId = ?1 and message.receiverUserId = ?2) " +
            "or (message.userId = ?2 and message.receiverUserId = ?1)")
    List<Messages> getUserMessages(final Long userId, final Long receiverUserId, final Pageable pageable);

    @Query(value = "select count(message) from Messages message where (message.userId = ?1 and message.receiverUserId = ?2) " +
            "or (message.userId = ?2 and message.receiverUserId = ?1)")
    int getCountMessages(final Long userId, final Long receiverUserId);
}
