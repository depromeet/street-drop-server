package com.depromeet.music.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

public class AppleMusicApiKeyGenerator {

    @Value("${apple.music.api.secret}")
    private String secret;

    @Value("${apple.music.api.key-id}")
    private String keyId;

    @Value("${apple.music.api.team-id}")
    private String teamId;
    private final String alg = SignatureAlgorithm.ES256.getValue();

    private final Long expiredTime = (long) (12 * 60 * 60);

    public String createToken() {
        Instant now = Instant.now();
        Instant expired = now.plusSeconds(expiredTime); // 12 hours expiration

        Date nowDate = Date.from(now);
        Date expiredDate = Date.from(expired);

        var key = secret
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\n", "");

        var keyBytes = Base64.getDecoder().decode(key);

        return Jwts.builder()
                .setHeaderParam("alg", alg)
                .setHeaderParam("kid", keyId)
                .setIssuer(teamId)
                .setExpiration(expiredDate)
                .setIssuedAt(nowDate)
                .signWith(SignatureAlgorithm.HS256, keyBytes)
                .compact();
    }
}
