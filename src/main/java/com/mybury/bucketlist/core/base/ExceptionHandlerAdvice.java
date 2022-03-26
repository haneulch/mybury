package com.mybury.bucketlist.core.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.exception.BaseException;
import com.mybury.bucketlist.core.v2.vo.ErrorResponse;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

	@ResponseBody
	@ExceptionHandler(BaseException.class)
	public ErrorResponse baseException(BaseException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return response;
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ErrorResponse exception(Exception ex) {
		ex.printStackTrace();
		ErrorResponse response = new ErrorResponse(ex.getMessage());
		return response;
	}

}