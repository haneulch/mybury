package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseRequestVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedoBucketlistRequestVO extends BaseRequestVO{

	  @JsonProperty
	  private String userId;
	  
	  @JsonProperty
	  private String bucketlistId;
}
