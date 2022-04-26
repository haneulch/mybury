package com.mybury.bucketlist.auth.vo;

import com.mybury.bucketlist.auth.v2.enums.CommonEnums;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "버킷리스트 검색")
public class SearchRequestDTO {

  @Parameter(description = "사용자 ID")
  String userId;

  @Parameter(description = "필터", schema = @Schema(implementation = CommonEnums.SearchFilter.class))
  private CommonEnums.SearchFilter filter;

  @Parameter(description = "검색어")
  private String searchText;
}
