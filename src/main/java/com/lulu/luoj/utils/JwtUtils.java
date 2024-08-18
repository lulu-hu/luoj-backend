package com.lulu.luoj.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 生成和解析JWT的工具类
 */
public class JwtUtils {
    private static final String SECRET = "lulu";


    /**
     * 生成token
     */
    public static String getToken(Map<String, Object> claims) {
        // 指定token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        JWTCreator.Builder builder = JWT.create();
        // 构建claims
        claims.forEach((key, value) -> builder.withClaim(key, value.toString()));
        return builder.withExpiresAt(calendar.getTime())
                .sign(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET));
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static DecodedJWT decode(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return jwtVerifier.verify(token);
    }

    /**
     *  验证token
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        try {
            JWTVerifier jwtVerifier =
                    JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            // 检查是否过期
            Date expiresAt = decodedJWT.getExpiresAt();
            return expiresAt != null & expiresAt.after(new Date());
        } catch (Exception e) {
            // 如果验证失败，则返回false
            return false;
        }
    }


}