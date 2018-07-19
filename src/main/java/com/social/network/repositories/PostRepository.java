package com.social.network.repositories;

import com.social.network.model.dao.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
