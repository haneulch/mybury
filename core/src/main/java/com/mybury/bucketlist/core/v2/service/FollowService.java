package com.mybury.bucketlist.core.v2.service;

import com.mybury.bucketlist.core.v2.vo.FollowRequest;
import com.mybury.bucketlist.core.v2.vo.StateResponse;
import com.mybury.bucketlist.core.v2.vo.UserRequest;

public interface FollowService {

	void save(FollowRequest request);

	StateResponse getFollowInfo(UserRequest request);

	void delete(FollowRequest request);
}