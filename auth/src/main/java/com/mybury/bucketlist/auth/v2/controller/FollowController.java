package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.FollowService;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/follow")
@ApiOperation(value = "follow apis", notes = "팔로우 관련 Controller")
public class FollowController {
	private final FollowService followService;
	
	FollowController(FollowService followService) {
		this.followService = followService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody FollowRequest request) { 
		followService.save(request);
		return ResponseUtils.success();
	}
	
	@PostMapping("/list")
	public ResponseEntity<Object> list(@RequestBody UserRequest request) {
		return ResponseUtils.success(followService.findByUserId(request.getUserId()));
	}
}
