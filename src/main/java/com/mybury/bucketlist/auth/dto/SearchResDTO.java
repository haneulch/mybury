package com.mybury.bucketlist.auth.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "검색 Response")
public class SearchResDTO {
  @Parameter(description = "버킷리스트 목록")
  List<BucketlistResDTO> bucketlists;

  @Parameter(description = "카테고리 목록")
  List<CategoryResDTO> categories;
}
