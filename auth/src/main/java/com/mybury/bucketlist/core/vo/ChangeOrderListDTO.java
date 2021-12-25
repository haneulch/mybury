package com.mybury.bucketlist.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

import lombok.Data;

@Data
@Schema(description = "버킷리스트 순서 변경")
public class ChangeOrderListDTO {
	@Schema(description = "사용자 ID")
	String userId;

	@Schema(description = "버킷리스트 ID")
	List<String> orders;
}

