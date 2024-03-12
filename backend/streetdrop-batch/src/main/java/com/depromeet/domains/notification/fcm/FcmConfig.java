package com.depromeet.domains.notification.fcm;

import com.depromeet.common.error.code.GlobalErrorCode;
import com.depromeet.common.error.exceptions.BusinessException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FcmConfig {

    @Value("${fcm.value}")
    private String FCM_PRIVATE_KEY_PATH;

    @PostConstruct
    private void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options =
                        FirebaseOptions.builder()
                                .setCredentials(
                                        GoogleCredentials.fromStream(
                                                new ClassPathResource(FCM_PRIVATE_KEY_PATH).getInputStream()))
                                .build();
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new BusinessException(GlobalErrorCode.BAD_REQUEST);
        }
    }
}
