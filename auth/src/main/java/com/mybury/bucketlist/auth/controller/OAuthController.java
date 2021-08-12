package com.mybury.bucketlist.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybury.bucketlist.auth.constants.ApiUriConstants;
import com.mybury.bucketlist.auth.vo.RefreshTokenRequestVO;
import com.mybury.bucketlist.auth.vo.RefreshTokenResponseVO;
import com.mybury.bucketlist.core.util.JwtUtils;

@RestController
public class OAuthController {

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping(ApiUriConstants.REFRESH_TOKEN)
	public RefreshTokenResponseVO refreshToken(@RequestBody RefreshTokenRequestVO requestVO) {
		String accessToken = jwtUtils.createAccessToken(requestVO.getUserId());
		String refreshToken = jwtUtils.createRefreshToken(accessToken);
		return new RefreshTokenResponseVO(accessToken, refreshToken);
	}
}
