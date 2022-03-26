package com.mybury.bucketlist.auth.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.auth.dto.BucketlistResDTO;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeResponseVO extends BaseResponseVO {

  @JsonProperty
  private List<BucketlistResDTO> bucketlists;

  @JsonProperty
  private boolean popupYn;

  public HomeResponseVO(String returnCode) {
    super(returnCode);
  }

  public HomeResponseVO(List<BucketlistResDTO> bucketlists, boolean popupYn) {
    super(ApiReturnCodes.OK);
    this.bucketlists = bucketlists;
    this.popupYn = popupYn;
  }

}
