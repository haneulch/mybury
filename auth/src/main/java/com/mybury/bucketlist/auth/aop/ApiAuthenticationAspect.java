package com.mybury.bucketlist.auth.aop;

import javax.servlet.http.HttpServletRequest;
import com.mybury.bucketlist.core.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ApiAuthenticationAspect {
	private final JwtUtils jwtUtils;
	private final HttpServletRequest request;

	@Before("@annotation(com.mybury.bucketlist.auth.annotation.AccessTokenCheck)")
	public void accessTokenCheck(JoinPoint joinPoint) {
		String accessToken = request.getHeader("X-Auth-Token");
		String userId = jwtUtils.isValidAccessToken(accessToken);
		request.setAttribute("userId", userId);
	}
}
