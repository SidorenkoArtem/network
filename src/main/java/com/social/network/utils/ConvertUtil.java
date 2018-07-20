package com.social.network.utils;

import com.social.network.model.dao.SocialGroup;
import com.social.network.model.dao.User;
import com.social.network.model.dao.UserFriends;
import com.social.network.model.dto.SocialGroupDto;
import com.social.network.model.dto.UserDto;
import com.social.network.model.dto.UserFriendDto;

public class ConvertUtil {
    public static UserDto convertToUserDto(final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setActive(user.getActive());
        userDto.setValidated(user.getValidated());
        userDto.setPhotoUrl(user.getPhotoUrl());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setCreateTimestamp(user.getCreateTimestamp());
        return userDto;
    }

    public static UserFriendDto convertToUserFriendDto(final UserFriends userFriend) {
        final UserFriendDto userFriendDto = new UserFriendDto();
        userFriendDto.setUserId(userFriend.getUserId());
        userFriendDto.setFriendId(userFriend.getFriendId());
        userFriendDto.setStatus(userFriend.getStatus());
        userFriendDto.setCreateTimestamp(userFriend.getCreateTimestamp());
        return userFriendDto;
    }

    public static SocialGroupDto convertToUserGroupDto(final SocialGroup socialGroup) {
        final SocialGroupDto socialGroupDto = new SocialGroupDto();
        socialGroupDto.setId(socialGroup.getId());
        socialGroupDto.setName(socialGroup.getName());
        socialGroupDto.setHeader(socialGroup.getHeader());
        socialGroupDto.setUserId(socialGroup.getUserId());
        socialGroupDto.setImageUrl(socialGroup.getImageUrl());
        socialGroupDto.setDescription(socialGroup.getDescription());
        socialGroupDto.setHideReaders(socialGroup.getHideReaders());
        socialGroupDto.setPrivateSocialGroup(socialGroup.getPrivateGroup());
        socialGroupDto.setCreateTimestamp(socialGroup.getCreateTimestamp());
        return socialGroupDto;
    }
}
