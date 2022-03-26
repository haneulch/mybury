package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "카테고리 정보")
public class CategoryVO {
  @Schema(description = "카테고리 id")
  private String id;

  @Schema(description = "카테고리명")
  private String name;

  @Schema(description = "카테고리 순서")
  private int priority;
}
