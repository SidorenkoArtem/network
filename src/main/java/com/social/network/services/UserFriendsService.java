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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFriendsService {

    private final UserFriendsRepository userFriendsRepository;

    public UserFriendsResponse getCurrentUserFriends(final Integer offset, final Integer limit) {
        final Long userId = 0L;//TODO
        return getUserFriends(userId ,offset, limit);
    }

    public UserFriendsResponse getOtherUserFriends(final Long userId ,final Integer offset, final Integer limit) {
        return getUserFriends(userId, offset, limit);
    }

    private UserFriendsResponse getUserFriends(final Long userId ,final Integer offset, final Integer limit) {
        final List<UserFriendDto> userFriends = userFriendsRepository.findUserFriendsByUserIdEqualsAndStatusEquals(userId, Status.APPROVED, PageRequest.of(offset, limit))
                .stream().map(ConvertUtil::convertToUserFriendDto).collect(Collectors.toList());
        final Integer count = userFriendsRepository.countByUserIdEqualsAndStatusEquals(userId, Status.APPROVED);
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
