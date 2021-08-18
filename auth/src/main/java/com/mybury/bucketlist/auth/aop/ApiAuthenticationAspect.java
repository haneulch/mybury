package com.mybury.bucketlist.auth.aop;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybury.bucketlist.core.util.JsonUtils;
import com.mybury.bucketlist.core.util.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ApiAuthenticationAspect {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private HttpServletRequest request;

	@Pointcut("@annotation(com.mybury.bucketlist.auth.annotation.AccessTokenCheck)")
	public void accessTokenCheck() {
	}

	@Before("accessTokenCheck()")
	public void beforeMethod(JoinPoint joinPoint) {
		String accessToken = request.getHeader("X-Auth-Token");
		jwtUtils.isValidAccessToken(accessToken, getUserId(joinPoint));
	}

	private String getUserId(JoinPoint joinPoint) {
		String userId = "";
		
		try {
			BufferedReader br = request.getReader();
			
			String content = br.lines().collect(Collectors.joining());
			
			br.close();
			
			JSONObject json = JsonUtils.toObject(content, JSONObject.class);
			userId = json.get("userId").toString();
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return userId;
	}
}
