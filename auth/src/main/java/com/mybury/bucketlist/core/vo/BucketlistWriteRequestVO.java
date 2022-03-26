package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.mybury.bucketlist.core.base.BaseRequestVO;

import java.util.Date;

@Getter
@Setter
public class BucketlistWriteRequestVO extends BaseRequestVO {

  @Schema(description = "제목")
  private String title;

  private boolean open;

  @Schema(name = "dDate", description = "디데이 일자")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dDate;

  @Schema(description = "목표 횟수")
  private int goalCount;

  @Schema(description = "메모")
  private String memo;

  @Schema(description = "카테고리 ID")
  private String categoryId;

  @Schema(description = "사용자 ID")
  private String userId;

  @Schema(description = "이미지 1")
  private MultipartFile image1;

  @Schema(description = "이미지 2")
  private MultipartFile image2;

  @Schema(description = "이미지 3")
  private MultipartFile image3;
}
