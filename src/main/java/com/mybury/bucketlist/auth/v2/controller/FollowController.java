package com.mybury.bucketlist.auth.v2.controller;

import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.FollowService;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;
import com.mybury.bucketlist.core.v2.vo.FollowResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow", description = "팔로우")
@RestController
@RequestMapping("/v2/follow")
@RequiredArgsConstructor
public class FollowController {
  private final FollowService followService;
  private final UserManager userService;

  @Operation(summary = "팔로우하기")
  @PostMapping("/save")
  public ResponseEntity<Object> save(@RequestBody FollowRequest request) {
    followService.save(request);
    return ResponseUtils.success();
  }

  @Operation(summary = "팔로우 취소")
  @DeleteMapping("/cancel")
  public ResponseEntity<Object> cancel(@RequestBody FollowRequest request) {
    followService.delete(request);
    return ResponseUtils.success();
  }

  @Operation(summary = "나의 팔로워/팔로잉 리스트")
  @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = FollowResponse.class))))
  @GetMapping("/{userId}/list")
  @Parameter(name = "userId", description = "사용자 ID", required = true, in = ParameterIn.PATH)
  @Parameter(name = "type", description = "팔로워/팔로우 유형(FOLLOWER/FOLLOWING)", required = true, in = ParameterIn.QUERY)
  public ResponseEntity<Object> list(@PathVariable String userId, @RequestParam String type) {
    return ResponseUtils.success(userService.findFollowings(userId));
  }
}
