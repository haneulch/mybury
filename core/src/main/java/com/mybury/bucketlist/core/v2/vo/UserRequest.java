package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "현재 사용자 ID")
@Data
public class UserRequest {

	@Schema(description = "사용자 ID")
	private String userId;
}