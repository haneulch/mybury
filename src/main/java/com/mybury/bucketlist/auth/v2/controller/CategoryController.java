package com.mybury.bucketlist.auth.v2.controller;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.CategoryService;
import com.mybury.bucketlist.core.v2.vo.CategoryPriorityRequest;
import com.mybury.bucketlist.core.v2.vo.CategoryRequest;
import com.mybury.bucketlist.core.v2.vo.NewCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category", description = "카테고리")
@RestController
@RequestMapping("/v2/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @AccessTokenCheck
  @Operation(summary = "my - category별 정보")
  //  @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))))
  @ApiResponse()
  @GetMapping("/{userId}")
  @Parameter(name = "userId", description = "사용자 ID", required = true, in = ParameterIn.PATH)
  public ResponseEntity<Object> category(@PathVariable String userId) {
    return ResponseUtils.success(categoryService.findCategoryInfo(userId));
  }

  @AccessTokenCheck
  @Operation(summary = "category 순서 변경")
  @PutMapping("/edit_priority")
  public ResponseEntity<Object> editPriority(@RequestBody CategoryPriorityRequest request) {
    //    categoryService.updateCategoryPriority(request);
    return ResponseUtils.success();
  }

  @AccessTokenCheck
  @Operation(summary = "category 명칭 변경")
  @PutMapping("/{categoryId}/edit_name")
  @Parameter(name = "categoryId", description = "카테고리 ID", required = true, in = ParameterIn.PATH)
  public ResponseEntity<Object> editName(@PathVariable String categoryId, @RequestBody CategoryRequest request) {
    //    categoryService.updateCategoryName(request);
    return ResponseUtils.success();
  }

  @AccessTokenCheck
  @Operation(summary = "category 삭제")
  @DeleteMapping("/{categoryId}/delete")
  @Parameter(name = "categoryId", description = "카테고리 ID", required = true, in = ParameterIn.PATH)
  public ResponseEntity<Object> delete(@PathVariable String categoryId) {
    //    categoryService.delete(categoryId);
    return ResponseUtils.success();
  }

  @AccessTokenCheck
  @Operation(summary = "category 추가")
  @PostMapping("/save")
  public ResponseEntity<Object> save(@RequestBody NewCategory request) {
    //    categoryService.save(request));
    return ResponseUtils.success();
  }
}
