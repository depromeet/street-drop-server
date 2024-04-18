package com.depromeet.music.service;


import com.depromeet.music.dto.response.MusicInfoListResponseDto;
import com.depromeet.music.dto.response.apple.AppleMusicResponseDto;
import com.depromeet.music.event.AppleMusicApiKeyRefreshEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AppleMusicService implements MusicService {

    private final ApplicationEventPublisher eventPublisher;

    private final AppleMusicConfig appleMusicConfig;

    @Cacheable(value = "music", key = "#keyword")
    public MusicInfoListResponseDto searchMusic(String keyword) {
        String appleMusicApiKey = appleMusicConfig.getAppleMusicApiKey();
        WebClient webClient = WebClient.builder().baseUrl("https://api.music.apple.com").build();

        Mono<AppleMusicResponseDto> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/catalog/kr/search")
                        .queryParam("types", "songs")
                        .queryParam("limit", 20)
                        .queryParam("term", keyword)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + appleMusicApiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse ->
                        {
                            if (clientResponse.statusCode() == HttpStatus.UNAUTHORIZED) {
                                eventPublisher.publishEvent(new AppleMusicApiKeyRefreshEvent());
                                throw new RuntimeException("error");
                            }
                            throw new RuntimeException("error");
                        }
                )
                .bodyToMono(AppleMusicResponseDto.class);
        return MusicInfoListResponseDto.ofAppleMusicResponseDto(response.block());
    }
}
