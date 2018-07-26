package com.social.network.repositories;

import com.social.network.model.dao.UserGroup;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    List<UserGroup> findSocialGroupsByUserIdEquals(final Long userId);
    Optional<UserGroup> findUserGroupByUserIdEqualsAndGroupIdEquals(final Long userId, final Long groupId);
}
