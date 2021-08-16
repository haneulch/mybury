package com.mybury.bucketlist.core.v2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "현재 사용자 ID")
public class UserRequest {
	
	@ApiModelProperty("사용자 ID")
	private String userId;
}