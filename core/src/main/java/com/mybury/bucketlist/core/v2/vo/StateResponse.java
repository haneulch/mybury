package com.mybury.bucketlist.core.v2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "My > 팔로우")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateResponse {

	private int followCount;
	
	private int followingCount;
	
	@ApiModelProperty("알림유무")
	private Character hasAlarm;
}
