package com.social.network.exceptions;

public class ConversationNotExistException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.CONVERSATION_NOT_EXIST;
    }
}
