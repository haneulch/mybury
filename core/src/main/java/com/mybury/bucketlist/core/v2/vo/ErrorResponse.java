package com.mybury.bucketlist.core.v2.vo;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
	int code;
	String message;
	HttpStatus status;
}
