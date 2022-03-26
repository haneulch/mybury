package com.mybury.bucketlist.core.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseRequestVO;
import com.mybury.bucketlist.core.domain.SupportHistory;
import com.mybury.bucketlist.core.domain.SupportItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupportHistoryRequestVO extends BaseRequestVO {
	@JsonProperty
	private int itemId;
	
	@JsonProperty
	private String userId;
	
	@JsonProperty
	private String token;
	
	@JsonProperty
	private String susYn;

    public SupportHistory toEntity() {
        return SupportHistory.builder()
        			.item(SupportItem.builder().id(itemId).build())
        			.userId(userId)
        			.token(token)
        			.susYn(susYn.charAt(0))
        			.build();
    }
}