package com.mybury.bucketlist.core.exception;

import com.mybury.bucketlist.core.constants.ApiReturnCodes;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getExceptionCode() {
        return ApiReturnCodes.ERROR_USER_ALREADY_EXISTS;
    }
}
