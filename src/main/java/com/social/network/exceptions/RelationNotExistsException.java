package com.social.network.exceptions;

public class RelationNotExistsException extends ApplicationException {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.RELATIONSHIP_NOT_EXIST;
    }
}
