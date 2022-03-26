package com.mybury.bucketlist.auth.controller;

import com.mybury.bucketlist.auth.vo.RefreshTokenRequestVO;
import com.mybury.bucketlist.auth.vo.RefreshTokenResponseVO;
import com.mybury.bucketlist.core.constants.MessageConstants;
import com.mybury.bucketlist.core.exception.InvalidTokenException;
import com.mybury.bucketlist.core.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {

	private final JwtUtils jwtUtils;

	@PostMapping("/refresh_token")
	public RefreshTokenResponseVO refreshToken(@RequestBody RefreshTokenRequestVO requestVO) {
		if (!jwtUtils.isValidateRefreshToken(requestVO)) {
			throw new InvalidTokenException(MessageConstants.INVALID_TOKEN);
		}
		String accessToken = jwtUtils.createAccessToken(requestVO.getUserId());
		String refreshToken = jwtUtils.createRefreshToken(requestVO.getUserId());
		return new RefreshTokenResponseVO(accessToken, refreshToken);
	}
}
