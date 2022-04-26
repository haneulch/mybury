package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * fileName      : FollowResponse
 * date         : 2022/04/17
 * description  :
 */
@Getter
@Schema(description = "팔로우/팔로워 정보")
public class FollowResponse {
  @Schema(description = "팔로우/팔로워 ID")
  private String id;

  @Schema(description = "팔로우/팔로워 이름")
  private String name;

  @Schema(description = "팔로우/팔로워 프로필 이미지")
  private String profileImage;
}
