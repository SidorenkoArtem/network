package com.social.network.exceptions;

public class UserNotConsistInConversationException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.USER_NOT_CONSISTS_IN_CONVERSATION;
    }
}
