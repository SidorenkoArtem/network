package com.social.network.services;

import com.social.network.exceptions.RequestOnFriendshipNotExists;
import com.social.network.model.dao.UserFriends;
import com.social.network.model.dto.UserFriendDto;
import com.social.network.model.enums.Status;
import com.social.network.model.requests.UserFriendsRequest;
import com.social.network.model.responces.UserFriendsResponse;
import com.social.network.repositories.UserFriendsRepository;
import com.social.network.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepository userFriendsRepository;

    public UserFriendsResponse getUserFriends() {
        final Long userId = 0L;//TODO
        final List<UserFriendDto> userFriends = userFriendsRepository.findUserFriendsByUserIdEquals(userId)
                .stream().map(ConvertUtil::convertToUserFriendDto).collect(Collectors.toList());
        final Integer count = userFriendsRepository.countByUserIdEquals(userId);
        return new UserFriendsResponse(userFriends, count);
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
}
