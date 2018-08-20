package com.social.network.exceptions;

public class MistakenRequestOnGiftException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.MISTAKEN_REQUEST_ON_GIFT;
    }
}
