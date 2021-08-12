package com.mybury.bucketlist.core.exception;

import com.mybury.bucketlist.core.constants.ApiReturnCodes;

public class InvalidTokenException extends BaseException {

  public InvalidTokenException() {
    super();
  }

  public InvalidTokenException(String message) {
    super(message);
  }

  @Override
  public String getExceptionCode() {
    return ApiReturnCodes.ERROR_INVALID_TOKEN;
  }
}
