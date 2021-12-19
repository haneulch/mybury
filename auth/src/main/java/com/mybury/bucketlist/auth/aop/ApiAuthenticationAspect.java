package com.mybury.bucketlist.auth.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybury.bucketlist.core.util.JwtUtils;
import java.io.BufferedReader;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ApiAuthenticationAspect {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private HttpServletRequest request;

	@Before("@annotation(com.mybury.bucketlist.auth.annotation.AccessTokenCheck)")
	public void accessTokenCheck(JoinPoint joinPoint) {
		String accessToken = request.getHeader("X-Auth-Token");
		jwtUtils.isValidAccessToken(accessToken, getUserId(joinPoint));
	}

	private String getUserId(JoinPoint joinPoint) {
		String userId = "";

		try {
			BufferedReader br = request.getReader();

			String content = br.lines().collect(Collectors.joining());

			br.close();

			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> map = mapper.readValue(content, Map.class);

			userId = map.get("userId");

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return userId;
	}
}
