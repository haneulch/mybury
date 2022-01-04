package com.mybury.bucketlist.core.exception;

import static com.mybury.bucketlist.core.constants.ApiReturnCodes.ERROR_INVALID_TOKEN;

public class InvalidTokenException extends BaseException {

	public InvalidTokenException() {
		super();
	}

	public InvalidTokenException(String message) {
		super(message);
	}

	@Override
	public String getExceptionCode() {
		return ERROR_INVALID_TOKEN;
	}
}
