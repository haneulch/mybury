package com.mybury.bucketlist.auth.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.service.BucketlistManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@Tag(name = "Bucketlist", description = "버킷리스트")
@RestController
@RequestMapping("/v2/bucketlist")
public class BucketlistController {
	private final BucketlistManager bucketlistManager;
	
	BucketlistController(BucketlistManager bucketlistManager) {
		this.bucketlistManager = bucketlistManager;
	}
	
	@AccessTokenCheck
	@Operation(summary = "dday별 버킷리스트")
	@GetMapping("/dday")
	public ResponseEntity<Object> dday(@RequestBody UserRequest request) {
		return ResponseUtils.success(bucketlistManager.findBydDateIsNotNullAndUser_Id(request.getUserId()));
	}
}