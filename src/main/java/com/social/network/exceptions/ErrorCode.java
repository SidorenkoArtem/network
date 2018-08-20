package com.social.network.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum  ErrorCode {
    INTERNAL_SERVER(1, "Server error. Please, try later"),
    NOT_VALID_REQUEST_DATA(2, "Not valid data"),
    USER_NOT_EXISTS(3, "User does not exist"),
    REQUEST_ON_FRIENDSHIP_NOT_EXISTS(4, "Request on friendship does not exist"),
    CAN_NOT_EXTRACT_LINK_DATA(5, "Can not extract link data"),
    SOCIAL_GROUP_NOT_EXISTS(6, "Social group doesn't exist"),
    REQUEST_TO_GROUP_NOT_EXIST(7, "Request to group doesn't exist"),
    USER_GROUP_NOT_EXIST(8, "User group doesn't exist"),
    CONVERSATION_NOT_EXIST(9, "Conversation does not exist"),
    NEED_TOKEN(10, "Need token"),
    INVALID_TOKEN(11, "Invalid token"),
    EMAIL_ALREADY_EXISTS(12, "User with this email already exists"),
    LOGIN_ALREADY_EXISTS(13, "User with this login already exists"),
    RELATIONSHIP_NOT_EXIST(14, "Relationship does not exist"),
    REQUEST_TO_SOCIAL_GROUP_ALREADY_EXISTS(15, "Request to social group already exists"),
    CONVERSATION_NOT_EXISTS(16, "Conversation does not exist"),
    USER_NOT_CONSISTS_IN_CONVERSATION(17, "User does not consist in conversation"),
    REQUEST_ON_GIFT_NOT_EXIST(18, "Request on gift does not exist"),
    MISTAKEN_REQUEST_ON_GIFT(19, "Mistaken request on gift");

    private final int errorCode;
    private final String message;
}
