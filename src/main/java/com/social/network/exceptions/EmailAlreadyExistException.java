package com.social.network.exceptions;

public class EmailAlreadyExistException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.EMAIL_ALREADY_EXISTS;
    }
}
