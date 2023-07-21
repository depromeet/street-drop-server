package com.depromeet.global.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtTokenProvider {
    private final JwtTokenConfig jwtTokenConfig;

    private String createToken(String id, Long expiredTime) {
        Claims claims = Jwts.claims();
        claims.put("id", id);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(jwtTokenConfig.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createAccessToken(String id) {
        return createToken(id, jwtTokenConfig.getAccessTokenValidTime());
    }

    public String createRefreshToken(String id) {
        return createToken(id, jwtTokenConfig.getRefreshTokenValidTime());
    }


}
