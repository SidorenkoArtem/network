package com.social.network.repositories;

import com.social.network.model.dao.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUsersByIdIn(final Collection<Long> userIds);
    Optional<User> findUserByLoginEquals(final String login);
    Boolean existsByEmailEquals(final String email);
    Boolean existsByLoginEquals(final String login);
}