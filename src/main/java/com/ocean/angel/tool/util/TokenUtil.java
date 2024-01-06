package com.ocean.angel.tool.util;

import com.ocean.angel.tool.domain.dto.AppInfo;
import com.ocean.angel.tool.domain.dto.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 令牌工具类
 */
@Slf4j
public class TokenUtil {

    /** 加密算法 */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /** 算法秘钥 */
    private static final String secret = "1HD@B9DHBYVGH5CGV#JK0";

    /** 令牌有效期 */
    private static final Long EXPIRATION = 7200L;

    /**
     * 生成token
     */
    public static TokenInfo getToken(AppInfo appInfo) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put("channelId", appInfo.getChannelId());
        claims.put("appId", appInfo.getAppId());
        claims.put("appSecret", appInfo.getAppSecret());

        String token = generateToken(appInfo.getChannelId().toString(), claims, EXPIRATION);
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setToken(token);
        tokenInfo.setExpiration(EXPIRATION);
        return tokenInfo;
    }

    /**
     * token有效期校验
     */
    public static Boolean isExpired(String token) {
        boolean result = true;
        final Claims claims = getClaimsFromToken(token);
        if(null != claims) {
            Date expireIn = claims.getExpiration();
            long diff = (expireIn.getTime() - System.currentTimeMillis()) / 1000;
            if (diff > 0) {
                result = false;
            }
        }
        return result;
    }

    /**
     * 获取appInfo
     */
    public static AppInfo getAppInfo(String token) {
        AppInfo appInfo = null;
        final Claims claims = getClaimsFromToken(token);
        if (null != claims) {
            appInfo = new AppInfo();
            appInfo.setChannelId(Long.parseLong(claims.getSubject()));
            appInfo.setAppId(claims.get("appId").toString());
            appInfo.setAppSecret(claims.get("appSecret").toString());
        }
        return appInfo;
    }

    /**
     * 获取channelId
     */
    public static Long getChannelId(String token) {
        Long channelId = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if(null == claims || null == claims.get("channelId")) {
                return null;
            }
            channelId = Long.parseLong(claims.get("channelId").toString());
        } catch (NumberFormatException e) {
            log.error("TokenUtil.getChannelId() error,", e);
        }
        return channelId;
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private static String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private static Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
