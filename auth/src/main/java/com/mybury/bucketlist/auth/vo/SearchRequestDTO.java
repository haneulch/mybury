package com.mybury.bucketlist.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "버킷리스트 검색")
public class SearchRequestDTO {

  @Schema(description = "사용자 ID")
  String userId;

  @Schema(description = "필터", allowableValues = {"all", "category", "dday"})
  private String filter;

  @Schema(description = "검색어")
  private String searchText;
}
