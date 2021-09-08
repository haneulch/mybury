package com.mybury.bucketlist.core.v2.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "카테고리 정보")
@Data
public class CategoryRequest {

	@ApiModelProperty("사용자 ID")
	private String userId;
	
	@ApiModelProperty("카테고리 ID")
	private String id;
	
	@ApiModelProperty("카테고리 이름")
	private String name;
}
