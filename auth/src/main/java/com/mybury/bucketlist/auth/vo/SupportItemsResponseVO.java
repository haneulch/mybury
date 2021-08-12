package com.mybury.bucketlist.auth.vo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.domain.SupportHistory;
import com.mybury.bucketlist.core.domain.SupportItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupportItemsResponseVO extends BaseResponseVO {
	@JsonProperty
	private List<SupportItem> supportItems;
	
	@JsonProperty
	private List<SupportHistory> recentSupport;
	
	@JsonProperty
	private String totalPrice;

	public SupportItemsResponseVO(List<SupportItem> supportItems) {
	    super(ApiReturnCodes.OK);
	    this.supportItems = supportItems;
	}
}