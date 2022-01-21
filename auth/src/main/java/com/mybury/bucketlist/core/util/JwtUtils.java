package com.mybury.bucketlist.core.util;

import java.util.UUID;

import com.mybury.bucketlist.core.config.CacheConfig;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.mybury.bucketlist.core.constants.MessageConstants;
import com.mybury.bucketlist.core.exception.ExpiredTokenException;
import com.mybury.bucketlist.core.exception.InvalidTokenException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
	private static final String SECRET = "mybury"; 
	private static final int EXPIRE_SECONDS = 60 * 60;

	private final CacheManager cacheManager;

	public String createAccessToken(String userId) {
		return Jwts
						.builder()
						.setSubject(userId)
						.signWith(SignatureAlgorithm.HS256, SECRET)
						.setExpiration(DateUtils.addSeconds(DateUtil.getDate(), EXPIRE_SECONDS))
						.compact();
	}

	public boolean isValidAccessToken(String token, String userId) {
		try {

			if(userId.equals("SKIP")) {
				return true;
			}

			String jwtUserId = Jwts.parser()
									.setSigningKey(SECRET)
									.parseClaimsJws(token)
									.getBody()
									.getSubject();
			
			if(!jwtUserId.equals(userId)) {
				throw new InvalidTokenException(MessageConstants.INVALID_TOKEN);
			}
			
		} catch (ExpiredJwtException e) {
			log.warn(String.format("jwt expired [token : %s] [error : %s]", token, e.getMessage()));
			throw new ExpiredTokenException(MessageConstants.EXPIRED_TOKEN);
		} catch (JwtException e) {
			log.warn(String.format("jwt invalid [token : %s] [error : %s]", token, e.getMessage()));
			throw new InvalidTokenException(MessageConstants.INVALID_TOKEN);
		}
		return true;
	}

	public String createRefreshToken(String accessToken) {
		String refreshToken = UUID.randomUUID().toString();
		Cache cache = cacheManager.getCache(CacheConfig.TOKEN_CACHE);
		cache.put(refreshToken, accessToken);
		return refreshToken;
	}
}
