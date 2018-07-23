package com.social.network.repositories;

import com.social.network.model.dao.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findPostByUserIdEqualsAndGroupIdEquals(final Long userId, final Long groupId);
}
