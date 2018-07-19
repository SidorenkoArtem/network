package com.social.network.repositories;

import com.social.network.model.dao.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
