package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * fileName      : CategoryResponse
 * date         : 2022/04/17
 * description  :
 */
@Getter
@Schema(description = "카테고리 정보")
public class CategoryResponse {
  @Schema(description = "ID")
  private String id;

  @Schema(description = "이름")
  private String name;

  @Schema(description = "포함된 버킷수")
  private int count;
}
