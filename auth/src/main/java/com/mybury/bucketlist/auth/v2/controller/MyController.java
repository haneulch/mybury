package com.mybury.bucketlist.auth.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.auth.v2.constants.MessageConstants;
import com.mybury.bucketlist.core.domain.BadgeUser;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.BadgeUserService;
import com.mybury.bucketlist.core.v2.service.FollowService;
import com.mybury.bucketlist.core.v2.vo.ErrorResponse;
import com.mybury.bucketlist.core.v2.vo.ProfileResponse;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@Tag(name = "My", description = "홈 > My")
@RestController
@RequestMapping("/v2/my")
public class MyController {
	private final UserManager userManager;
	private final BadgeUserService badgeUserService;
	private final FollowService followService;
	
	MyController(UserManager userManager
			, BadgeUserService badgeUserService
			, FollowService followService) {
		this.userManager = userManager;
		this.badgeUserService = badgeUserService;
		this.followService = followService;
	}

	@AccessTokenCheck
	@Operation(summary = "my - 프로필 정보")
	@GetMapping("/profile_info")
	public ResponseEntity<Object> profileInfo(@RequestBody UserRequest request) { 
		User user = userManager.findById(request.getUserId());
		
		if(user == null) {
			ErrorResponse e = new ErrorResponse(MessageConstants.BAD_REQUEST_USER);
			return ResponseUtils.error(e);
		}
		
		BadgeUser badgeUser = badgeUserService.findByUseYnAndUserId(request.getUserId());
		
		ProfileResponse response = ProfileResponse.builder()
										.nickname(user.getName())
										.profileImg(user.getImgUrl())
										.bio(user.getBio())
										.badge(badgeUser.getBadge())
										.build();
		
		return ResponseUtils.success(response);
	}
	
	@AccessTokenCheck
	@Operation(summary = "my - 팔로우 정보")
	@GetMapping("/state")
	public ResponseEntity<Object> state(@RequestBody UserRequest request) {
		return ResponseUtils.success(followService.getFollowInfo(request));
	}
	
	@AccessTokenCheck
	@Operation(summary = "my - 알림 유무 변경")
	@PutMapping("/changeAlarm")
	public ResponseEntity<Object> changeAlarm(@RequestBody UserRequest request) {
		userManager.updateAlarmYn(request.getUserId());
		return ResponseUtils.success();
	}
}
