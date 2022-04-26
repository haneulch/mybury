package com.mybury.bucketlist.core.vo;

import com.mybury.bucketlist.auth.v2.enums.CommonEnums;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "버킷리스트 목록 조회")
public class HomeRequestVO {

  @Parameter(description = "사용자 ID")
  private String userId;

  @Parameter(description = "필터", schema = @Schema(implementation = CommonEnums.BucketFilter.class))
  private CommonEnums.BucketFilter filter;

  @Parameter(description = "정렬 순서", schema = @Schema(allowableValues = {"updatedDt", "createdDt", "custom"}))
  private String sort;
}
