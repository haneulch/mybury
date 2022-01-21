package com.mybury.bucketlist.core.repository;

import java.util.Date;
import java.util.List;

import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.vo.HomeRequestVO;

public interface BucketlistRepositoryCustom {

  List<Bucketlist> getBucketlists(HomeRequestVO requestVO);

  boolean existsPopupBucketlist(String userId, int popupPeriod);

  List<Bucketlist> getDDayBucketlist(String userId, String filter);

  List<Bucketlist> getBucketlistsByDDate(Date date, String userId);

  String getLastBucketlistId();

  int getStartedBucketlistCount(String userId);

  int getCompletedBucketlistCount(String userId);

  List<Bucketlist> getBucketlistByCategoryId(String categoryId);
}
