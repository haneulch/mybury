package com.mybury.bucketlist.core.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;



public class ResponseUtils<T> extends ResponseEntity<T> {

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
		return new ResponseUtils(obj, HttpStatus.BAD_REQUEST);
	}
}
