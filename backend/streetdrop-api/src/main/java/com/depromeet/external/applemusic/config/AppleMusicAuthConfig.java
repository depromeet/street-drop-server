package com.depromeet.external.applemusic.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppleMusicAuthConfig implements RequestInterceptor {

    private static final String HEADER_NAME ="Authorization";
    private static final String AUTH_TYPE = "Bearer";

    @Value("${apple.music.api.key}")
    private String value;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(HEADER_NAME, AUTH_TYPE + " " + value);
    }

}
