package com.mybury.bucketlist.core.v2.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	public ErrorResponse(String message) {
		this.message = message;
		this.status = HttpStatus.BAD_REQUEST;
		this.code = this.status.value();
	}
	
	int code;
	String message;
	HttpStatus status;
}
