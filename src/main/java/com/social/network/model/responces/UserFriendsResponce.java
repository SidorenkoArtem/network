package com.social.network.model.responces;

import com.social.network.model.dto.UserFriendDto;
import lombok.Data;

import java.util.List;

@Data
public class UserFriendsResponce {
    private final List<UserFriendDto> userFriends;
    private final Integer count;
}
