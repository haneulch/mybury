package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.FollowService;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Follow", tags = {"팔로우"})
@RestController
@RequestMapping("/v2/follow")
public class FollowController {
	private final FollowService followService;
	private final UserManager userService;
	
	FollowController(
			FollowService followService
			, UserManager userService) {
		this.followService = followService;
		this.userService = userService;
	}
	
	@ApiOperation("팔로우하기")
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody FollowRequest request) { 
		followService.save(request);
		return ResponseUtils.success();
	}
	
	@ApiOperation("나의 팔로잉 리스트")
	@GetMapping("/list")
	public ResponseEntity<Object> list(@RequestBody UserRequest request) {
		return ResponseUtils.success(userService.findFollowings(request.getUserId()));
	}
	
	@ApiOperation("나의 팔로워 리스트")
	@GetMapping("/list_followers")
	public ResponseEntity<Object> listFollowers(@RequestBody UserRequest request) {
		return ResponseUtils.success(userService.findFollowers(request.getUserId()));
	}
}
