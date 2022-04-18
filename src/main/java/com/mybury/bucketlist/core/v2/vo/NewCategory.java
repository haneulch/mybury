package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * fileName      : NewCategory
 * date         : 2022/04/17
 * description  :
 */
@Schema(description = "카테고리 생성 Request")
public class NewCategory {
  @Schema(description = "사용자 ID")
  private String userId;

  @Schema(description = "이름")
  private String name;
}
