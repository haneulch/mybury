package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "dday별 버킷리스트 조회")
public class DDayRequestVO {
  @Parameter(description = "사용자 id")
  private String userId;

  @Parameter(description = "필터", schema = @Schema(allowableValues = {"minus", "plus"}))
  private String filter;
}
