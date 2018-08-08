package com.social.network.utils;

import com.social.network.model.dao.*;
import com.social.network.model.dto.*;

public class ConvertUtil {
    public static UserDto convertToUserDto(final User user) {
        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setFirstName(user.getFirstName());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setBirthday(user.getBirthday());
        userDto.setSex(user.getSex());
        userDto.setEmail(user.getEmail());
        userDto.setPhotoUrl(user.getPhotoUrl());
        userDto.setRole(user.getRole());
        userDto.setCountry(user.getCountry());
        userDto.setCity(user.getCity());

        userDto.setActive(user.getActive());
        userDto.setValidated(user.getValidated());
        userDto.setDeleted(user.getDeleted());

        userDto.setShowGroups(user.getPagePermission().getShowGroups());
        userDto.setShowWall(user.getPagePermission().getShowWall());
        userDto.setShowLocation(user.getPagePermission().getShowLocation());
        userDto.setShowGifts(user.getPagePermission().getShowGifts());

        userDto.setCreateTimestamp(user.getCreateTimestamp());
        return userDto;
    }

    public static SimpleUserDto convertToSimpleUserDto(final User user) {
        return new SimpleUserDto(user.getId(), user.getFirstName(), user.getName(), user.getPhotoUrl());
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

    public static GiftDto convertToGiftDto(final Gift gift) {
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

    public static ConversationDto convertToConversationDto(final Conversation conversation) {
        final ConversationDto conversationDto = new ConversationDto();
        conversationDto.setId(conversation.getId());
        conversationDto.setUserId(conversation.getUserId());
        conversationDto.setUserIdInterlocutor(conversation.getUserIdInterlocutor());
        conversationDto.setCreateTimestamp(conversation.getCreateTimestamp());
        conversationDto.setUpdateTimestamp(conversation.getUpdateTimestamp());
        return conversationDto;
    }

    public static MessagesDto convertToMessagesDto(final Messages message) {
        final MessagesDto messagesDto = new MessagesDto();
        messagesDto.setId(message.getId());
        messagesDto.setUserId(message.getUserId());
        messagesDto.setConversationId(message.getConversationId());
        messagesDto.setFileUrl(message.getFileUrl());
        messagesDto.setText(message.getText());
        messagesDto.setCreateTimestamp(message.getCreateTimestamp());
        messagesDto.setUpdateTimestamp(message.getUpdateTimestamp());
        return messagesDto;
    }
}
