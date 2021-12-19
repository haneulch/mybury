package com.mybury.bucketlist.auth.v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.BadgeService;
import com.mybury.bucketlist.core.v2.vo.UserRequest;


@Tag(name = "Badge", description = "뱃지")
@RestController
@RequestMapping("/v2/badge")
public class BadgeController {
	
	private final BadgeService badgeService;
	
	BadgeController(BadgeService badgeService) {
		this.badgeService = badgeService;
	}

	@PostMapping("/save")
	@Operation(summary = "뱃지 정보 저장", hidden = true)
	public ResponseEntity<Object> save(@RequestBody Badge badge) {
		badgeService.save(badge);
		return ResponseUtils.success();
	}
	
	@AccessTokenCheck
	@Operation(summary = "뱃지 리스트")
	@GetMapping("/list")
	public ResponseEntity<Object> list(@RequestBody UserRequest request) {
		return ResponseUtils.success(badgeService.findByUserId(request.getUserId()));
	}
}
