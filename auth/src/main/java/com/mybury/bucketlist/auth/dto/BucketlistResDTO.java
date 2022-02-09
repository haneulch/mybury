package com.mybury.bucketlist.auth.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import com.mysema.query.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * fileName      : BucketlistResDTO
 * date         : 2022/01/22
 * description  :
 */
@Getter
@Setter
@Schema(description = "버킷리스트 정보")
@ToString
public class BucketlistResDTO extends CommonDTO {
  @Schema(description = "버킷리스트 id")
  private String id;

  @Schema(description = "제목")
  private String title;

  @Schema(description = "고정여부")
  private boolean pin;

  @Schema(description = "상태")
  private String status;

  @JsonProperty("dDate")
  @Schema(description = "d-date")
  private Date dDate;

  @JsonProperty("dDay")
  @Schema(description = "d-day")
  private int dDay;

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

  public BucketlistResDTO(BucketlistVO bucketlistVO) {
    this.dataCopy(bucketlistVO);
  }

  public BucketlistResDTO init() {
    setDDay(DateUtil.getDday(getDDate()));
    return this;
  }

  @QueryProjection
  public BucketlistResDTO(String id, String title, boolean pin, String status, Date dDate, int userCount,
    int goalCount, Date completedDt, int orderSeq, String categoryName) {
    this.id = id;
    this.title = title;
    this.pin = pin;
    this.status = status;
    this.dDate = dDate;
    if(dDate != null) {
      this.dDay = DateUtil.getDday(dDate);
    }
    this.userCount = userCount;
    this.goalCount = goalCount;
    this.completedDt = completedDt;
    this.orderSeq = orderSeq;
    this.categoryName = categoryName;
  }
}
