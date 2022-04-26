package com.mybury.bucketlist.auth.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * fileName      : CategoryResDTO
 * date         : 2022/01/22
 * description  :
 */
@Getter
@AllArgsConstructor
@Schema(description = "카테고리 정보")
public class CategoryResDTO {
  @Parameter(description = "카테고리 id")
  private String id;

  @Parameter(description = "카테고리명")
  private String name;

  @Parameter(description = "해당 카테고리에 포함된 버킷수")
  private int count;
}
