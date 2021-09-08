package com.mybury.bucketlist.core.v2.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "카테고리 변경된 순서")
@Data
public class CategoryPriorityRequest {

	@ApiModelProperty("사용자 ID")
	private String userId;
	
	@ApiModelProperty("카테고리 ID 리스트")
	private List<String> categories;
}
