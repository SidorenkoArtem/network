package com.social.network.exceptions;

public class RequestToSocialGroupAlreadyExistException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.REQUEST_TO_SOCIAL_GROUP_ALREADY_EXISTS;
    }
}
