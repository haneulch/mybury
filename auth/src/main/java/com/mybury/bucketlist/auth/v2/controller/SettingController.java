package com.mybury.bucketlist.auth.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.UserService;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

@Tag(name = "setting")
@RestController
@RequestMapping("/v2/setting")
public class SettingController {
	private UserService userService;
	
	SettingController(UserService userService) {
		this.userService = userService;
	}
	
	@AccessTokenCheck
	@Operation(summary = "회원탈퇴")
	@DeleteMapping("/leave")
	public ResponseEntity<Object> leave(@RequestBody UserRequest request) {
		userService.removeUserInfo(request);
		return ResponseUtils.success();
	}
}
