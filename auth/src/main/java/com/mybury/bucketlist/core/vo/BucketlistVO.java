package com.mybury.bucketlist.core.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BucketlistVO {
  private String id;
  private String title;
  private boolean pin;
  private String status;
  private Date dDate;
  private int userCount;
  private int goalCount;
  private Date completedDt;
  private int orderSeq;
  private String categoryName;
}
