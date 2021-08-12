package com.mybury.bucketlist.core.exception;

import com.mybury.bucketlist.core.constants.ApiReturnCodes;

public class ExpiredTokenException extends BaseException {

  public ExpiredTokenException() {
    super();
  }

  public ExpiredTokenException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return ApiReturnCodes.ERROR_EXPIRED_TOKEN;
  }
}
