package com.social.network.exceptions;

public class SocialGroupNotExistException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.SOCIAL_GROUP_NOT_EXISTS;
    }
}
