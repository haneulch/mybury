package com.mybury.bucketlist.auth.dto;

import javax.persistence.Transient;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.util.DateUtil;
import com.mybury.bucketlist.core.vo.BucketlistVO;
import com.mysema.query.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.Parameter;
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
  @Parameter(description = "버킷리스트 id")
  private String id;

  @Parameter(description = "제목")
  private String title;

  @Parameter(description = "고정여부")
  private boolean pin;

  @Parameter(description = "상태")
  private String status;

  @JsonProperty("dDate")
  @Parameter(description = "d-date")
  private Date dDate;

  @Transient
  @JsonIgnore
  private Date dueDate;

  @JsonProperty("dDay")
  @Parameter(description = "d-day")
  private Integer dDay;

  @Parameter(description = "달성횟수")
  private int userCount;

  @Parameter(description = "목표횟수")
  private int goalCount;

  @Parameter(description = "완료일시")
  private Date completedDt;

  @Parameter(description = "정렬순서")
  private int orderSeq;

  @Parameter(description = "카테고리 id")
  private String categoryName;

  public BucketlistResDTO(BucketlistVO bucketlistVO) {
    this.dataCopy(bucketlistVO);
  }

  @QueryProjection
  public BucketlistResDTO(String id, String title, boolean pin, String status, Date dueDate, int userCount,
    int goalCount, Date completedDt, int orderSeq, String categoryName) {
    this.id = id;
    this.title = title;
    this.pin = pin;
    this.status = status;
    if (dueDate != null) {
      this.dDate = dueDate;
      this.dueDate = dueDate;
      this.dDay = DateUtil.getDday(dueDate);
    }
    this.userCount = userCount;
    this.goalCount = goalCount;
    this.completedDt = completedDt;
    this.orderSeq = orderSeq;
    this.categoryName = categoryName;
  }

  public BucketlistResDTO init() {
    if (getDueDate() != null) {
      setDDate(getDueDate());
      setDDay(DateUtil.getDday(getDueDate()));
    }
    return this;
  }
}
