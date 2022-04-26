package com.mybury.bucketlist.core.vo;

import java.util.Date;
import com.mybury.bucketlist.core.base.BaseRequestVO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BucketlistWriteRequestVO extends BaseRequestVO {

  @Schema(description = "제목")
  private String title;

  private boolean open;

  @Parameter(name = "dDate", description = "디데이 일자")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dDate;

  @Parameter(description = "목표 횟수")
  private int goalCount;

  @Parameter(description = "메모")
  private String memo;

  @Parameter(description = "카테고리 ID")
  private String categoryId;

  @Parameter(description = "사용자 ID")
  private String userId;

  @Parameter(description = "이미지 1")
  private MultipartFile image1;

  @Parameter(description = "이미지 2")
  private MultipartFile image2;

  @Parameter(description = "이미지 3")
  private MultipartFile image3;
}
