package com.mybury.bucketlist.core.util;

import com.mybury.bucketlist.core.vo.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.mybury.bucketlist.core.v2.vo.ErrorResponse;


public class ResponseUtils<T> extends ResponseEntity<T>{

	public ResponseUtils(HttpStatus status) {
		super(status);
	}
	
	public ResponseUtils(T data, HttpStatus status) {
		super(data, status);
	}
	
	public ResponseUtils(T data) {
		super(data, HttpStatus.OK);
	}
	
	public ResponseUtils(T data, MultiValueMap<String, String> headers, HttpStatus status) {
		super(data, headers, status);
	}
	
	public static ResponseEntity<Object> success() {
		return new ResponseUtils<>(HttpStatus.OK);
	}
	
	public static ResponseEntity<Object> success(Object obj) {
		return new ResponseUtils(obj, HttpStatus.OK);
	}
	
	public static ResponseEntity<Object> error(Object obj) {
		if(obj instanceof ErrorResponse) {
			return new ResponseUtils(obj, ((ErrorResponse) obj).getStatus());
		}
		
		return new ResponseUtils(obj, HttpStatus.BAD_REQUEST);
	}
}
