package com.social.network.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum  ErrorCode {
    USER_NOT_EXISTS(0, "User does not exist"),
    REQUEST_ON_FRIENDSHIP_NOT_EXISTS(1, "Request on friendship does not exist"),
    CAN_NOT_EXTRACT_LINK_DATA(2, "Can not extract link data");

    private final int errorCode;
    private final String message;
}
