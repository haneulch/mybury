package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "팔로우 요청")
@Data
public class FollowRequest {

	@Schema(description = "현재 사용자 ID")
	private String userId;

	@Schema(description = "팔로우(취소)할 사용자 ID")
	private String followingId;
}