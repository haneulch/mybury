package com.mybury.bucketlist.auth.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybury.bucketlist.core.util.JwtUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

			userId = request.getParameter("userId");

			if(userId == null) {

				if(request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
					return "SKIP";
				}

				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = mapper.readValue(IOUtils.toString(request.getReader()), Map.class);

				userId = map.get("userId");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return "SKIP";
		}

		return userId;
	}
}
