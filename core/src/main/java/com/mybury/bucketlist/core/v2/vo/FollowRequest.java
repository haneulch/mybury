package com.mybury.bucketlist.core.v2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "팔로우 요청")
@Data
public class FollowRequest {
	
	@ApiModelProperty("현재 사용자 ID")
	private String userId;
	
	@ApiModelProperty("팔로우할 사용자 ID")
	private String followingId;
}