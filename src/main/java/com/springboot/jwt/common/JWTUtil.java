package com.springboot.jwt.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    //过期时间设置
    private static final long EXPIRE_TIME = 5*60*1000;

    /**
     *
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verif(String token, String username, String secret){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier =JWT.require(algorithm)
                .withClaim("username",username)
                .build();
        DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception e) {
      return false;
    }
    }

    /**
     *
     * @param token
     * @return
     */
  public static String getUserName(String token) {
      try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("username").asString();
      } catch (JWTDecodeException e) {
          return null;
      }
  }

    /**
     *
     * @param username
     * @param secret
     */
  public static String sign(String username, String secret) {
      try {
          Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
          Algorithm algorithm = Algorithm.HMAC256(secret);
          return JWT.create().withClaim("uername", username).withExpiresAt(date).sign(algorithm);
      } catch (UnsupportedEncodingException e) {
         return null;
      }
  }

}
