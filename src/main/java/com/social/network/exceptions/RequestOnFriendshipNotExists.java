package com.social.network.exceptions;

public class RequestOnFriendshipNotExists extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.REQUEST_ON_FRIENDSHIP_NOT_EXISTS;
    }
}
