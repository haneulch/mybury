package com.mybury.bucketlist.core.v2.service;

import java.util.List;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.projections.BucketlistResult;
import com.mybury.bucketlist.core.repository.BucketlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * fileName      : BucketlistService
 * date         : 2022/04/05
 * description  : Bucketlist Service Class
 */
@Service
@RequiredArgsConstructor
public class BucketlistService {
  private final BucketlistRepository bucketlistRepository;

  public List<BucketlistResult> getBucketlistByUserId(String userId) {
    return bucketlistRepository.findProjectionByDueDateIsNotNullAndUser_Id(userId);
  }
}
