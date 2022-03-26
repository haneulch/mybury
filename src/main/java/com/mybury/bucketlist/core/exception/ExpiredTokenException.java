package com.mybury.bucketlist.core.exception;

import static com.mybury.bucketlist.core.constants.ApiReturnCodes.ERROR_EXPIRED_TOKEN;

public class ExpiredTokenException extends BaseException {

  public ExpiredTokenException() {
    super();
  }

  public ExpiredTokenException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return ERROR_EXPIRED_TOKEN;
  }
}
