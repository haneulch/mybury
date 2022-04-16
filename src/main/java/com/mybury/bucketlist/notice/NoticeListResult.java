package com.mybury.bucketlist.notice;

import java.util.Date;

/**
 * fileName      : NoticeListResult
 * date         : 2022/04/02
 * description  : Notice List Result Class
 */
public interface NoticeListResult {
  int getSeq();

  String getTag();

  String getTitle();

  Date getCreatedDt();
}
