package com.social.network.repositories;

import com.social.network.model.dao.TimePK;
import com.social.network.model.dao.UserFriends;
import com.social.network.model.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFriendsRepository extends CrudRepository<UserFriends, TimePK> {

    List<UserFriends> findUserFriendsByUserIdEqualsAndStatusEquals(final Long userId, final Status status, Pageable pageable);

    int countByUserIdEqualsAndStatusEquals(final Long userId, Status status);

    Optional<UserFriends> findUserFriendsByUserIdEqualsAndFriendIdEquals(final Long userId, final Long friendId);

//    @Query("Select UserFriends from UserFriends where ")
//    UserFriends findUserFriendsByUserIdInAndFriendIdIn();
}
