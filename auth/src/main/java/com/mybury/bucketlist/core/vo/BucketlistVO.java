package com.mybury.bucketlist.core.vo;

import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "버킷리스트 정보")
public class BucketlistVO {
  @Schema(description = "버킷리스트 id")
  private String id;

  @Schema(description = "제목")
  private String title;

  @Schema(description = "고정여부")
  private boolean pin;

  @Schema(description = "상태")
  private String status;

  @Schema(description = "d-date")
  private Date dDate;

  @Schema(description = "달성횟수")
  private int userCount;

  @Schema(description = "목표횟수")
  private int goalCount;

  @Schema(description = "완료일시")
  private Date completedDt;

  @Schema(description = "정렬순서")
  private int orderSeq;

  @Schema(description = "카테고리 id")
  private String categoryName;
}
