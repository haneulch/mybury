package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseRequestVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "버킷리스트 완료 request")
public class CompleteBucketlistRequestVO extends BaseRequestVO {
  @JsonProperty
  @Schema(description = "버킷리스트 id")
  private String bucketlistId;
}
