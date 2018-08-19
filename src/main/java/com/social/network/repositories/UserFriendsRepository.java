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

    @Query("select userFriend from UserFriends userFriend where (userFriend.userId = ?1 or userFriend.friendId = ?2) and" +
            " userFriend.status = ?3")
    List<UserFriends> findUserFriendsByUserIdEqualsOrFriendIdEqualsAndStatusEquals(final Long userId, final Long userIdToo,
                final Status status, Pageable pageable);
    int countByUserIdEqualsOrFriendIdEqualsAndStatusEquals(final Long userId,final Long userIdToo, Status status);
    Optional<UserFriends> findUserFriendsByUserIdEqualsAndFriendIdEquals(final Long userId, final Long friendId);

    @Query("select userFriends from UserFriends userFriends where (userFriends.userId = ?1 and userFriends.friendId = ?2) " +
    " or (userFriends.userId = ?2 and userFriends.friendId = ?1)")
    Optional<UserFriends> getRelationship(final Long currentUserId, final Long userId);
}
