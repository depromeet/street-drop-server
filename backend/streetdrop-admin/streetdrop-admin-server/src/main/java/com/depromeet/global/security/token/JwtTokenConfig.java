package com.depromeet.global.security.token;


import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Configuration
@Getter
public class JwtTokenConfig {
    private final Long accessTokenValidTime;
    private final Long refreshTokenValidTime;
    private final String secretKey;

    public JwtTokenConfig(@Value("${jwt.access-token-expiration}") Long accessTokenValidTime,
                          @Value("${jwt.refresh-token-expiration}") Long refreshTokenValidTime,
                          @Value("${jwt.secret-key}") String secretKey) {
        this.accessTokenValidTime = accessTokenValidTime;
        this.refreshTokenValidTime = refreshTokenValidTime;
        this.secretKey = secretKey;
    }

    public Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build();
    }
}