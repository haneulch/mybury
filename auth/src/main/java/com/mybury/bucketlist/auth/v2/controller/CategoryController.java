package com.mybury.bucketlist.auth.v2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.domain.CategoryInfo;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.CategoryService;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Category", tags = {"카테고리"})
@RestController
@RequestMapping("/v2/category")
public class CategoryController {
	
	private final CategoryService categoryService; 
	
	CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@AccessTokenCheck
	@ApiOperation(value = "my - category별 정보", response = CategoryInfo.class)
	@GetMapping("/my")
	public ResponseEntity<Object> category(@RequestBody UserRequest request) {
		return ResponseUtils.success(categoryService.findCategoryInfo(request.getUserId()));
	}
	
	@AccessTokenCheck
	@ApiOperation(value = "category 순서 변경")
	@PutMapping("/edit_priority")
	public ResponseEntity<Object> editPriority(@RequestBody CategoryPriorityRequest request) {
		categoryService.updateCategoryPriority(request);
		return ResponseUtils.success();
	}
	
	@AccessTokenCheck
	@ApiOperation(value = "category 명칭 변경")
	@PutMapping("/edit_name")
	public ResponseEntity<Object> editName(@RequestBody CategoryRequest request) {
		categoryService.updateCategoryName(request);
		return ResponseUtils.success();
	}
	
	@AccessTokenCheck
	@ApiOperation(value = "category 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestBody CategoryRequest request) {
		categoryService.delete(request.getId());
		return ResponseUtils.success();
	}
	
	@AccessTokenCheck
	@ApiOperation(value = "category 추가")
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody CategoryRequest request) {
		request.setId(categoryService.save(request));
		return ResponseUtils.success(request);
	}
}
