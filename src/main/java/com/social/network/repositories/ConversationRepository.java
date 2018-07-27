package com.social.network.repositories;

import com.social.network.model.dao.Conversation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

}
