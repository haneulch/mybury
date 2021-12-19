package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

import lombok.Data;

@Data
@Schema(description = "카테고리 변경된 순서")
public class CategoryPriorityRequest {

	@Schema(description = "사용자 ID")
	private String userId;

	@Schema(description = "카테고리 ID 리스트")
	private List<String> categories;
}
