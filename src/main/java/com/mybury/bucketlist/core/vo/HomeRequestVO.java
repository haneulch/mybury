package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "버킷리스트 목록 조회")
public class HomeRequestVO {

  @Schema(description = "사용자 ID")
  private String userId;

  @Schema(description = "필터", allowableValues = {"started", "completed", "all"})
  private String filter;

  @Schema(description = "정렬 순서", allowableValues = {"updatedDt", "createdDt", "custom"})
  private String sort;
}
