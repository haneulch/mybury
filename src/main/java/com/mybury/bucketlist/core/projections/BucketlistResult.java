package com.mybury.bucketlist.core.projections;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;
import java.util.Date;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

/**
 * fileName      : BucketlistResult
 * date         : 2022/04/05
 * description  :
 */
public interface BucketlistResult {
  String getId();
  String getTitle();
  boolean getOpen();
  boolean getPin();
  String getStatus();
  Date getDueDate();
  int getUserCount();
  int getGoalCount();
  Date getCompletedDt();
  Character getBucketType();
  int getOrderSeq();
  String getMemo();
  String getImgUrl1();
  String getImgUrl2();
  String getImgUrl3();
  String getCategory_Name();
}
