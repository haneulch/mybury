package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DDayResponseVO extends BaseResponseVO {

  @JsonProperty
  private List<DDayVO> dDayBucketlists;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DDayVO {

    @JsonProperty
    private int day;

    @JsonProperty
    private List<Bucketlist> bucketlists;

  }

  public DDayResponseVO(List<DDayVO> dDayBucketlists) {
    super(ApiReturnCodes.OK);
    this.dDayBucketlists = dDayBucketlists;
  }
}
