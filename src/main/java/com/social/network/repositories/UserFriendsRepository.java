package com.social.network.repositories;

import com.social.network.model.dao.TimePK;
import com.social.network.model.dao.UserFriends;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepository extends CrudRepository<UserFriends, TimePK> {
}
