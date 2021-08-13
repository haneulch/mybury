package com.mybury.bucketlist.core.v2.vo;

import lombok.Data;

@Data
public class FollowRequest {
	private String userId;
	private String followingId;
}