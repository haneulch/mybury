package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.service.CategoryManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Category", tags = {"카테고리"})
@RestController
@RequestMapping("/v2/category")
public class CategoryController {
	
	private final CategoryManager categoryManager; 
	
	CategoryController(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	@AccessTokenCheck
	@ApiOperation(value = "my - category별 정보", response = CategoryInfo.class)
	@PostMapping("/my")
	public ResponseEntity<Object> category(@RequestBody UserRequest request) {
		return ResponseUtils.success(categoryManager.findCategoryInfo(request.getUserId()));
	}
}
