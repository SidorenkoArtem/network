package com.social.network.exceptions;

public class InvalidAuthorizationHeaderException extends ApplicationException {

    public InvalidAuthorizationHeaderException(){

    }

    public InvalidAuthorizationHeaderException(final String message) {
        super(message);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_TOKEN;
    }
}
