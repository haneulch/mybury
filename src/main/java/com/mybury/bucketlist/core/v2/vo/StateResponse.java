package com.mybury.bucketlist.core.v2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "My > 팔로우")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateResponse {

  @Schema(description = "팔로우수")
  private int followCount;

  @Schema(description = "팔로잉수")
  private int followingCount;

  @Schema(description = "알림유무")
  private Character hasAlarm;
}
