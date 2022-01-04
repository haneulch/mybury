package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "dday별 버킷리스트 조회")
public class DDayRequestVO {
  @Schema(description = "사용자 id")
  private String userId;

  @Schema(description = "필터", allowableValues = {"minus", "plus"})
  private String filter;
}
