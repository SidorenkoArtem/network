package com.social.network.exceptions;

public class RequestOnGiftNotExistsException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.REQUEST_ON_GIFT_NOT_EXIST;
    }
}
