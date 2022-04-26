package com.mybury.bucketlist.core.v2.vo;

import java.util.List;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "카테고리 변경된 순서")
public class CategoryPriorityRequest {

  @Parameter(description = "사용자 ID")
  private String userId;

  @Parameter(description = "카테고리 ID 리스트")
  private List<String> categories;
}
