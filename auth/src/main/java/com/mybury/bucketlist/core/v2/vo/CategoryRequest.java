package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "카테고리 정보")
@Data
public class CategoryRequest {

	@Schema(description = "사용자 ID")
	private String userId;

	@Schema(description = "카테고리 ID")
	private String id;

	@Schema(description = "카테고리 이름")
	private String name;
}
