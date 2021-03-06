package com.mybury.bucketlist.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenResponseVO extends BaseResponseVO {

	@JsonProperty
	private String accessToken;

	@JsonProperty
	private String refreshToken;

	public RefreshTokenResponseVO(String accessToken, String refreshToken) {
		super(ApiReturnCodes.OK);
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
