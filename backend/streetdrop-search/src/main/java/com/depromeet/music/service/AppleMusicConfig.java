package com.depromeet.music.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppleMusicConfig {

    @Value("${apple.music.api.key}")
    private String appleMusicApiKey;

    public String getAppleMusicApiKey() {
        return appleMusicApiKey;
    }

}
