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
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
   // private static final long EXPIRE_TIME = 10 * 1000;
    private static final String SECRET="admintor123service";  //密钥盐
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verif(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
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
     * 签名生成
     * @param username
     */
        public static String sign(String username) {
        String token = null;
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create().withIssuer("auth0").withClaim("username", username).withExpiresAt(date).sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
