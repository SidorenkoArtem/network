package com.social.network.exceptions;

public class UserGroupNotExistException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_GROUP_NOT_EXIST;
    }
}
