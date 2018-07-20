package com.social.network.exceptions;

public class UserNotExistsException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_NOT_EXISTS;
    }
}
