package com.mybury.bucketlist.auth.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * fileName      : DdayBucketlistResponse
 * date         : 2022/04/17
 * description  :
 */
@Getter
@Schema(description = "dday 버킷리스트 정보")
public class DdayBucketlistResponse {
  @Parameter(description = "버킷리스트 ID")
  private String id;

  @Parameter(description = "dday")
  private int dday;

  @Parameter(description = "버킷리스트 제목")
  private String title;
}
