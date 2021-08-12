package com.mybury.bucketlist.core.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.mybury.bucketlist.core.base.BaseRequestVO;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BucketlistWriteRequestVO extends BaseRequestVO {

  private String title;

  private boolean open;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dDate;

  private int goalCount;

  private String memo;

  private String categoryId;

  private String userId;

  private MultipartFile image1;

  private MultipartFile image2;

  private MultipartFile image3;

}
