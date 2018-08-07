package com.social.network.repositories;

import com.social.network.model.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByLoginEquals(final String login);
    Boolean existsByEmailEquals(final String email);
    Boolean existsByLoginEquals(final String login);
}