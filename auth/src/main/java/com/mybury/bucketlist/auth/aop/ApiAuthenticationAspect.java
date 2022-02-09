package com.mybury.bucketlist.auth.aop;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybury.bucketlist.core.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.MediaType;
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

				InputStream is = request.getInputStream();
				BufferedReader br = null;
				StringBuilder sb = new StringBuilder();
				if(is != null) {
					try {
						br = new BufferedReader(new InputStreamReader(is));
						char[] charBuffer = new char[128];
						int bytes = -1;
						while ((bytes = br.read(charBuffer)) > 0) {
							sb.append(charBuffer, 0, bytes);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						assert br != null;
						br.close();
					}
				}

				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = mapper.readValue(sb.toString(), Map.class);

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
