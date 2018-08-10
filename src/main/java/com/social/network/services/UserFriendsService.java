package com.social.network.services;

import com.social.network.exceptions.RequestOnFriendshipNotExists;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserFriends;
import com.social.network.model.dto.UserFriendDto;
import com.social.network.model.enums.Status;
import com.social.network.model.requests.UserFriendsRequest;
import com.social.network.model.responces.UserFriendsResponse;
import com.social.network.repositories.UserFriendsRepository;
import com.social.network.repositories.UserRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepository userFriendsRepository;
    private final UserRepository userRepository;

    public UserFriendsResponse getCurrentUserFriends(final Integer offset, final Integer limit) {
        final Long userId = 0L;//TODO
        return getUserFriends(userId ,offset, limit);
    }

    public UserFriendsResponse getOtherUserFriends(final Long userId ,final Integer offset, final Integer limit) {
        return getUserFriends(userId, offset, limit);
    }

    private UserFriendsResponse getUserFriends(final Long userId ,final Integer offset, final Integer limit) {
        final List<UserFriends> userFriends = userFriendsRepository.findUserFriendsByUserIdEqualsOrFriendIdEqualsAndStatusEquals(userId, userId, Status.APPROVED, PageRequest.of(offset, limit));
        final Set<Long> userFriendIds = getUserFriendIds(userFriends, userId);
        final List<User> users = userRepository.findUsersByIdIn(userFriendIds);
        final Map<Long, User> userIdAndUserMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        final List<UserFriendDto> userFriendDtos = userFriendIds.stream().filter(e -> !e.equals(userId))
                .map(e -> {
                    final User user = userIdAndUserMap.get(e);
                    return ConvertUtil.convertToUserFriendDto(userId, user);
                }).collect(Collectors.toList());
        final Integer count = userFriendsRepository.countByUserIdEqualsOrFriendIdEqualsAndStatusEquals(userId, userId, Status.APPROVED);
        return new UserFriendsResponse(userFriendDtos, count);
    }

    private Set<Long> getUserFriendIds(final List<UserFriends> userFriends, final Long userId) {
        final Set<Long> userFriendIds = userFriends.stream().map(UserFriends::getFriendId).filter(e -> !e.equals(userId)).collect(Collectors.toSet());
        userFriendIds.addAll(userFriends.stream().map(UserFriends::getUserId).filter(e -> !e.equals(userId)).collect(Collectors.toSet()));
        return userFriendIds;
    }

    public void addRequestOnFriendship(final UserFriendsRequest request) {
        final Long userId = 0L;//TODO
        final UserFriends requestOnFriendship = new UserFriends();
        requestOnFriendship.setUserId(userId);
        requestOnFriendship.setFriendId(request.getFriendId());
        requestOnFriendship.setStatus(Status.REQUESTED);
        userFriendsRepository.save(requestOnFriendship);
    }

    public void changeRequestOnFriendship(final Long userId, final Status status) {
        final Long friendId = 0L;//TODO
        final UserFriends requestOnFriendship = userFriendsRepository.findUserFriendsByUserIdEqualsAndFriendIdEquals(userId, friendId)
                .orElseThrow(RequestOnFriendshipNotExists::new);
        requestOnFriendship.setStatus(status);
        userFriendsRepository.save(requestOnFriendship);
    }

    public void deleteRequestOnFriendship(final Long userId) {//TODO all method
        final Long currentUserId = 0L;//TODO

        //userFriendsRepository.delete();
    }

    @Transactional(readOnly = true)
    public UserFriendsResponse getFriends(final Long userId, final Integer page, final Integer limit) {
        final List<UserFriends> userFriends = userFriendsRepository.findUserFriendsByUserIdEqualsOrFriendIdEqualsAndStatusEquals(
                userId, userId, Status.APPROVED, PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createTimestamp"))));
        final Set<Long> userFriendIds = getUserFriendIds(userFriends, userId);
        final List<User> users = userRepository.findUsersByIdIn(userFriendIds);
        return new UserFriendsResponse(users.stream().map(e -> {
            return ConvertUtil.convertToUserFriendDto(userId, e);
        }).collect(Collectors.toList()),
                userFriendsRepository.countByUserIdEqualsOrFriendIdEqualsAndStatusEquals(userId, userId, Status.APPROVED));
    }
}
