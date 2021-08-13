package com.mybury.bucketlist.core.v2.service;

import java.util.List;

import com.mybury.bucketlist.core.domain.Follow;
import com.mybury.bucketlist.core.v2.vo.FollowRequest;

public interface FollowService {
	List<Follow> findByUserId(String userId);

	void save(FollowRequest request);
}