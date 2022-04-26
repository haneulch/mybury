package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "현재 사용자 ID")
public class UserRequest {

  @Parameter(description = "사용자 ID")
  private String userId;
}
