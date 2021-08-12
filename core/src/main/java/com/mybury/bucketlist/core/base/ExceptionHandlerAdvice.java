package com.mybury.bucketlist.core.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.exception.BaseException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerAdvice {

  @ResponseBody
  @ExceptionHandler(BaseException.class)
  public BaseResponseVO baseException(BaseException ex) {
    String returnCode = ex.getExceptionCode();
    String message = ex.getMessage();
    return new BaseResponseVO(returnCode, message);
  }
}
