package com.mybury.bucketlist.core.util;

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
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
  private static final String SECRET = "!AKDLQJFLTLZMFLTZL123";
  private static final String SECRET_FOR_REFRESH = "!AKDLQJFLTLZMFLTZL123_RF";

  /**
   * AccessToken 생성 (한시간 만료)
   *
   * @param userId User ID
   * @return AccessToken
   */
  public String createAccessToken(String userId) {
    return Jwts
      .builder()
      .setSubject(userId)
      .signWith(SignatureAlgorithm.HS256, SECRET)
      .setExpiration(DateUtils.addHours(DateUtil.getDate(), 1))
      .compact();
  }

  /**
   * RefreshToken 생성 (한 달 만료)
   *
   * @param userId
   * @return
   */
  public String createRefreshToken(String userId) {
    return Jwts
      .builder()
      .setSubject(userId)
      .signWith(SignatureAlgorithm.HS256, SECRET_FOR_REFRESH)
      .setExpiration(DateUtils.addMonths(DateUtil.getDate(), 1))
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

  public String isValidateRefreshToken(RefreshTokenRequestVO requestVO) {
    try {
      return Jwts.parser()
        .setSigningKey(SECRET_FOR_REFRESH)
        .parseClaimsJws(requestVO.getRefreshToken())
        .getBody()
        .getSubject();
    } catch (ExpiredJwtException e) {
      log.warn(String.format("jwt expired [token : %s] [error : %s]", requestVO.getRefreshToken(), e.getMessage()));
      throw new ExpiredTokenException(MessageConstants.EXPIRED_TOKEN);
    } catch (JwtException e) {
      log.warn(String.format("jwt invalid [token : %s] [error : %s]", requestVO.getRefreshToken(), e.getMessage()));
      throw new InvalidTokenException(MessageConstants.INVALID_TOKEN);
    }
  }
}
