package com.mybury.bucketlist.core.util;

import static com.mybury.bucketlist.auth.config.CacheConfig.TOKEN_CACHE;

import java.util.UUID;
import com.mybury.bucketlist.auth.vo.RefreshTokenRequestVO;
import com.mybury.bucketlist.core.constants.MessageConstants;
import com.mybury.bucketlist.core.exception.ExpiredTokenException;
import com.mybury.bucketlist.core.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
  private static final String SECRET = "!AKDLQJFLTLZMFLTZL123";
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

  public String isValidAccessToken(String token) {
    try {
      return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();

    } catch (ExpiredJwtException e) {
      log.warn(String.format("jwt expired [token : %s] [error : %s]", token, e.getMessage()));
      throw new ExpiredTokenException(MessageConstants.EXPIRED_TOKEN);
    } catch (JwtException e) {
      log.warn(String.format("jwt invalid [token : %s] [error : %s]", token, e.getMessage()));
      throw new InvalidTokenException(MessageConstants.INVALID_TOKEN);
    }
  }

  public boolean isValidateRefreshToken(RefreshTokenRequestVO requestVO) {
    Cache cache = cacheManager.getCache(TOKEN_CACHE);
    if (cache == null)
      return false;
    String cachedRefreshToken = (String) cache.get(requestVO.getUserId()).get();
    if (cachedRefreshToken == null)
      return false;
    return requestVO.getRefreshToken().equals(cachedRefreshToken);
  }

  public String createRefreshToken(String userId) {
    String refreshToken = UUID.randomUUID().toString();
    Cache cache = cacheManager.getCache(TOKEN_CACHE);
    cache.put(userId, refreshToken);
    return refreshToken;
  }
}
