package com.mybury.bucketlist.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * fileName      : DdayBucketlistResponse
 * date         : 2022/04/17
 * description  :
 */
@Schema(description = "dday 버킷리스트 정보")
public class DdayBucketlistResponse {
  @Schema(description = "버킷리스트 ID")
  private String id;

  @Schema(description = "dday")
  private int dday;

  @Schema(description = "버킷리스트 제목")
  private String title;
}
