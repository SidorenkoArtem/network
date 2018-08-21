package com.social.network.utils;

import com.social.network.model.dao.*;
import com.social.network.model.dto.*;

public class ConvertUtil {

    private ConvertUtil() {
    }

    public static UserDto convertToUserDto(final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setFirstName(user.getFirstName());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setBirthday(user.getBirthday());
        userDto.setSex(user.getSex());
        userDto.setPhotoUrl(user.getPhotoUrl());
        userDto.setRole(user.getRole());
        userDto.setCountry(user.getCountry());
        userDto.setCity(user.getCity());

        userDto.setActive(user.getActive());
        userDto.setValidated(user.getValidated());
        userDto.setDeleted(user.getDeleted());

        userDto.setShowFriends(user.getPagePermission().getShowFriends());
        userDto.setShowPage(user.getPagePermission().getShowPage());
        userDto.setShowGroups(user.getPagePermission().getShowGroups());
        userDto.setShowWall(user.getPagePermission().getShowWall());
        userDto.setShowLocation(user.getPagePermission().getShowLocation());
        userDto.setShowGifts(user.getPagePermission().getShowGifts());
        userDto.setShowBirthday(user.getPagePermission().getShowBirthday());
        userDto.setShowSex(user.getPagePermission().getShowSex());

        userDto.setCreateTimestamp(user.getCreateTimestamp());
        return userDto;
    }

    public static SimpleUserDto convertToSimpleUserDto(final User user) {
        final SimpleUserDto simpleUser = new SimpleUserDto();
        simpleUser.setName(user.getName());
        simpleUser.setFirstName(user.getFirstName());
        simpleUser.setUserId(user.getId());
        simpleUser.setBirthday(user.getBirthday());
        simpleUser.setCountry(user.getCountry());
        simpleUser.setCity(user.getCity());
        simpleUser.setUserPermission(convertToPagePermissionDto(user.getPagePermission()));
        return simpleUser;
    }

    public static SimpleUserDto convertToSimpleUserDto(final User user, final Boolean friend) {
        final SimpleUserDto simpleUser = new SimpleUserDto();
        simpleUser.setName(user.getName());
        simpleUser.setFirstName(user.getFirstName());
        simpleUser.setUserId(user.getId());
        simpleUser.setBirthday(user.getBirthday());
        simpleUser.setCountry(user.getCountry());
        simpleUser.setCity(user.getCity());
        simpleUser.setFriend(friend);
        simpleUser.setPhotoUrl(user.getPhotoUrl());
        simpleUser.setGender(user.getSex());
        simpleUser.setUserPermission(convertToPagePermissionDto(user.getPagePermission()));
        return simpleUser;
    }

    private static PagePermissionDto convertToPagePermissionDto(final PagePermission pagePermission) {
        final PagePermissionDto permission = new PagePermissionDto();
        permission.setShowFriends(pagePermission.getShowFriends());
        permission.setShowGifts(pagePermission.getShowGifts());
        permission.setShowGroups(pagePermission.getShowGroups());
        permission.setShowLocation(pagePermission.getShowLocation());
        permission.setShowWall(pagePermission.getShowWall());
        permission.setShowBirthday(pagePermission.getShowBirthday());
        permission.setShowGender(pagePermission.getShowSex());
        return permission;
    }

    public static UserFriendDto convertToUserFriendDto(final Long userId, final User user) {
        final UserFriendDto userFriendDto = new UserFriendDto();
        userFriendDto.setFriend(convertToSimpleUserDto(user));
        userFriendDto.setUserId(userId);
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

    public static SimpleSocialGroupDto convertToSimpleSocialGroupDto(final SocialGroup socialGroup) {
        final SimpleSocialGroupDto socialGroupDto = new SimpleSocialGroupDto();
        socialGroupDto.setId(socialGroup.getId());
        socialGroupDto.setUserId(socialGroup.getUserId());
        socialGroupDto.setName(socialGroup.getName());
        socialGroupDto.setDescription(socialGroup.getDescription());
        socialGroupDto.setImageUrl(socialGroup.getImageUrl());
        return socialGroupDto;
    }

    private static GiftDto convertToGiftDto(final Gift gift) {
        final GiftDto giftDto = new GiftDto();
        giftDto.setId(gift.getId());
        giftDto.setImageUrl(gift.getImageUrl());
        giftDto.setCreateTimestamp(gift.getCreateTimestamp());
        return giftDto;
    }

    public static UserGiftDto convertToUserGiftDto(final UserGift userGift, final Gift gift, final User user) {
        final UserGiftDto userGiftDto = new UserGiftDto();
        userGiftDto.setId(userGift.getId());
        userGiftDto.setGiftUserFromId(userGift.getGiftFromId());
        userGiftDto.setGift(convertToGiftDto(gift));
        userGiftDto.setCreateTimestamp(userGift.getCreateTimestamp());
        userGiftDto.setUser(convertToSimpleUserDto(user));
        return userGiftDto;
    }

    public static MessageDto convertToMessagesDto(final Messages message, final User user) {
        final MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setUser(convertToSimpleUserDto(user));
        messageDto.setFileUrl(message.getFileUrl());
        messageDto.setText(message.getText());
        messageDto.setCreateTimestamp(message.getCreateTimestamp());
        return messageDto;
    }

    public static WallPostDto convertToWallPostDto(final WallPost wallPost, final User user) {
        final WallPostDto wallPostDto = new WallPostDto();
        wallPostDto.setText(wallPost.getText());
        wallPostDto.setFileUrl(wallPost.getFileUrl());
        wallPostDto.setUser(convertToSimpleUserDto(user));
        wallPostDto.setPostId(wallPost.getId());
        return wallPostDto;
    }

    public static ConversationDto convertToConversationDto(final UserConversation userConversation, final User creator, final User companion) {
        final ConversationDto conversation = new ConversationDto();
        conversation.setId(userConversation.getId());
        conversation.setCreatorConversation(convertToSimpleUserDto(creator));
        conversation.setCompanionConversation(convertToSimpleUserDto(companion));
        return conversation;
    }
}
