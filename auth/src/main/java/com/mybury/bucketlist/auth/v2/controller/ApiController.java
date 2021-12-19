package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.util.ResponseUtils;

@RestController
@RequestMapping("/v2/api")
public class ApiController {

	@PostMapping("/save")
	public ResponseEntity<Object> save() {
		
		return ResponseUtils.success("OK");
	}
}
