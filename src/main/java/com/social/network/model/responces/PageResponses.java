package com.social.network.model.responces;

import com.social.network.model.dto.SimpleSocialGroupDto;
import com.social.network.model.dto.UserDto;
import com.social.network.model.dto.UserFriendDto;
import com.social.network.model.dto.UserGiftDto;
import lombok.Data;
import java.util.List;

@Data
public class PageResponses {
    private UserDto user;
    private List<UserGiftDto> gifts;
    private Integer countGift;
    private List<SimpleSocialGroupDto> socialGroups;
    private Integer countSocialGroups;
    private List<UserFriendDto> friends;
    private Integer countFriends;
    private Boolean currentUser;
    private Boolean authenticated;
}
