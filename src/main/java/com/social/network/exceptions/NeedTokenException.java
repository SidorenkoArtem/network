package com.social.network.exceptions;

public class NeedTokenException extends ApplicationException {
  @Override
  public ErrorCode getErrorCode() {
    return ErrorCode.NEED_TOKEN;
  }
}
