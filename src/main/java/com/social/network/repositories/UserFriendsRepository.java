package com.social.network.repositories;

import com.social.network.model.dao.TimePK;
import com.social.network.model.dao.UserFriends;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFriendsRepository extends CrudRepository<UserFriends, TimePK> {

    List<UserFriends> findUserFriendsByUserIdEquals(final Long userId);

    int countByUserIdEquals(final Long userId);

    Optional<UserFriends> findUserFriendsByUserIdEqualsAndFriendIdEquals(final Long userId, final Long friendId);

//    @Query("Select UserFriends from UserFriends where ")
//    UserFriends findUserFriendsByUserIdInAndFriendIdIn();
}
