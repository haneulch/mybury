package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseRequestVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BucketlistRemoveRequestVO extends BaseRequestVO {

  @JsonProperty
  private String userId;
}
