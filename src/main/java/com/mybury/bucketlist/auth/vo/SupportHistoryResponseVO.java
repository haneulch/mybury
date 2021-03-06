package com.mybury.bucketlist.auth.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.SupportHistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportHistoryResponseVO extends BaseResponseVO {
	@JsonProperty
	private List<SupportHistory> supportHistoryList;

	public SupportHistoryResponseVO(List<SupportHistory> supportHistoryList) {
	    super(ApiReturnCodes.OK);
	    this.supportHistoryList = supportHistoryList;
	}
}