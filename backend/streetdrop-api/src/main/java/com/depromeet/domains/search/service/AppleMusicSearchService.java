package com.depromeet.domains.search.service;


import com.depromeet.domains.search.config.AppleMusicConfig;
import com.depromeet.domains.search.response.MusicInfoListResponseDto;
import com.depromeet.domains.search.response.apple.AppleMusicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AppleMusicSearchService implements MusicSearchService {

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

                .bodyToMono(AppleMusicResponseDto.class);
        return MusicInfoListResponseDto.ofAppleMusicResponseDto(response.block());
    }
}
