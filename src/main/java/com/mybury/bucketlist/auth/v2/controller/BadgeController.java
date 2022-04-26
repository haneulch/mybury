package com.mybury.bucketlist.auth.v2.controller;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.core.domain.Badge;
import com.mybury.bucketlist.core.util.ResponseUtils;
import com.mybury.bucketlist.core.v2.service.BadgeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Badge", description = "뱃지")
@RestController
@RequestMapping("/v2/badge")
@RequiredArgsConstructor
public class BadgeController {
  private final BadgeService badgeService;

  @AccessTokenCheck
  @Operation(summary = "뱃지 리스트")
  @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Badge.class))))
  @GetMapping("/{userId}/list")
  @Parameter(name = "userId", description = "사용자 ID", required = true, in = ParameterIn.PATH)
  public ResponseEntity<Object> list(@PathVariable String userId) {
    return ResponseUtils.success(badgeService.findByUserId(userId));
  }

  @PostMapping("/save")
  @Operation(summary = "뱃지 정보 저장", hidden = true)
  public ResponseEntity<Object> save(@RequestBody Badge badge) {
    badgeService.save(badge);
    return ResponseUtils.success();
  }
}
