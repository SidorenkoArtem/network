package com.social.network.exceptions;

public class LoginAlreadyExitstsException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.LOGIN_ALREADY_EXISTS;
    }
}
