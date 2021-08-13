package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.domain.BadgeUser;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.BadgeUserService;
import com.mybury.bucketlist.core.v2.vo.ProfileResponse;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/my")
@ApiOperation(value = "my apis", notes = "My 관련 Controller")
public class MyController {
	private final UserManager userManager;
	private final BadgeUserService badgeUserService;
	
	MyController(UserManager userManager, BadgeUserService badgeUserService) {
		this.userManager = userManager;
		this.badgeUserService = badgeUserService;
	}

	@PostMapping("/profile_info")
	public ResponseEntity<Object> profileInfo(@RequestBody UserRequest request) { 
		User user = userManager.getUserById(request.getUserId());
		BadgeUser badgeUser = badgeUserService.findByUseYnAndUserId(request.getUserId());
		
		ProfileResponse response = ProfileResponse.builder()
										.nickname(user.getName())
										.profileImg(user.getImgUrl())
										.bio(user.getBio())
										.badge(badgeUser.getBadge())
										.build();
		
		return ResponseUtils.success(response);
	}

}
