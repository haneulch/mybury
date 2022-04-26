package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * fileName      : BadgeResponse
 * date         : 2022/04/17
 * description  :
 */
@Getter
@Schema(description = "뱃지 정보")
public class BadgeResponse {
  @Schema(description = "이름")
  private String title;

  @Schema(description = "이미지")
  private String image;
}
