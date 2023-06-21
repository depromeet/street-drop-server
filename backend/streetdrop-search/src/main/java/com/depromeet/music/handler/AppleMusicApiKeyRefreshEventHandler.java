package com.depromeet.music.handler;

import com.depromeet.music.event.AppleMusicApiKeyRefreshEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppleMusicApiKeyRefreshEventHandler {
    @EventListener
    public void refreshAppleMusicApiKey(AppleMusicApiKeyRefreshEvent event) {
        log.info("refresh apple music api key");
    }
}
