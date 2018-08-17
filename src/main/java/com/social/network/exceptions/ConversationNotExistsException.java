package com.social.network.exceptions;

public class ConversationNotExistsException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.CONVERSATION_NOT_EXISTS;
    }
}
