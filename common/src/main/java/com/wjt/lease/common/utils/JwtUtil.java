package com.wjt.lease.common.utils;

import cn.hutool.core.util.ObjUtil;
import com.wjt.lease.common.exception.LeaseException;
import com.wjt.lease.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @ClassName JwtUtil
 * @Description: JWT工具类
 * @Author 86178
 * @Date 2025/3/8 00088:22
 * @Version 1.0
 */
public class JwtUtil {

    private static SecretKey secretKey = Keys.hmacShaKeyFor("uA4pktjne7HrhQNdfrh8xaqbnzjx3UXw".getBytes());


    public static String createToken(Long userId, String userName) {
        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 24 * 365L)) // 设置过期时间
                .setSubject("LOGIN_USER") // 设置主题
                .claim("userId", userId) // 设置自定义属性 userId
                .claim("userName", userName) // 设置自定义属性 userName
                .signWith(secretKey, SignatureAlgorithm.HS256) // 设置签名算法和密钥
                .compact();
        return jwt;
    }

    public static Claims parseToken(String token) {
        if (ObjUtil.isNull(token)) throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);

        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static void main(String[] args) {
        String token = createToken(2L, "user");
        System.out.println(token);
    }
}
