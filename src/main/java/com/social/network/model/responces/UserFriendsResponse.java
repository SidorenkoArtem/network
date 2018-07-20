package com.social.network.model.responces;

import com.social.network.model.dto.UserFriendDto;
import lombok.Data;

import java.util.List;

@Data
public class UserFriendsResponse {
    private final List<UserFriendDto> userFriends;
    private final Integer count;
}
