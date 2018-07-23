package com.social.network.repositories;

import com.social.network.model.dao.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    List<UserGroup> findSocialGroupsByUserIdEquals(final Long userId);
    int countByUserIdEquals(final Long userId);
}
