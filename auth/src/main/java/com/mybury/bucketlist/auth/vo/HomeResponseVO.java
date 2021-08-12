package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeResponseVO extends BaseResponseVO {

  @JsonProperty
  private List<Bucketlist> bucketlists;

  @JsonProperty
  private boolean popupYn;

  public HomeResponseVO(String returnCode) {
    super(returnCode);
  }

  public HomeResponseVO(List<Bucketlist> bucketlists, boolean popupYn) {
    super(ApiReturnCodes.OK);
    this.bucketlists = bucketlists;
    this.popupYn = popupYn;
  }

}
