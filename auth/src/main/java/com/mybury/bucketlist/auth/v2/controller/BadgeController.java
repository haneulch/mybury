package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.BadgeService;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/badge")
@ApiOperation(value = "badge apis", notes = "Badge 관련 Controller")
public class BadgeController {
	
	private final BadgeService badgeService;
	
	BadgeController(BadgeService badgeService) {
		this.badgeService = badgeService;
	}

	@PostMapping("/save")
	@ApiOperation(value = "뱃지 정보 저장", hidden = true)
	public ResponseEntity<Object> save(@RequestBody Badge badge) {
		badgeService.save(badge);
		return ResponseUtils.success();
	}
}
