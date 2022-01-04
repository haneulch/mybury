package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseRequestVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "다시 도전하기 request")
public class RedoBucketlistRequestVO extends BaseRequestVO{
	  @JsonProperty
		@Schema(description = "사용자 id")
	  private String userId;
	  
	  @JsonProperty
		@Schema(description = "버킷리스트 id")
	  private String bucketlistId;
}
