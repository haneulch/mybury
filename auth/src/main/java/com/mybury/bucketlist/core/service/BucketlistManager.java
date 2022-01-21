package com.mybury.bucketlist.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mybury.bucketlist.auth.vo.SearchRequestDTO;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.mybury.bucketlist.core.vo.BucketlistWriteRequestVO;
import com.mybury.bucketlist.core.vo.HomeRequestVO;

public interface BucketlistManager {

  List<Bucketlist> getBucketlists(HomeRequestVO requestVO);

  List<Bucketlist> getBucketlistByCategoryId(String categoryId);

  boolean existsPopupBucketlist(String userId, int popupPeriod);

  List<Bucketlist> getDDayBucketlist(String userId, String filter);

  List<Bucketlist> getBucketlistsByDDate(Date date, String userId);

  Bucketlist getBucketlistById(String bucketlistId);

  void writeBucketlist(BucketlistWriteRequestVO requestVO);

  void saveBucketlist(Bucketlist bucketlist);

  void modifyBucketlist(BucketlistModifyRequestVO requestVO);

  void deleteBucketlist(String bucketlistId);

  int getStartedBucklistCount(String userId);

  int getCompletedBucketlistCount(String userId);

  String getLastBucketlistId();

  Map<String, List<Bucketlist>> findBydDateIsNotNullAndUser_Id(String userId);
}
