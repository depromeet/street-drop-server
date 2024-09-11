package com.depromeet.external.applemusic.service;

import com.depromeet.domains.recommend.dto.response.ArtistInfoListResponseDto;
import com.depromeet.domains.recommend.dto.response.MusicInfoListResponseDto;
import com.depromeet.external.feign.client.AppleMusicFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient appleMusicFeignClient;

    public MusicInfoListResponseDto getSongCharts(int limit) {
        var response = appleMusicFeignClient.getSongCharts("songs", limit);
        return MusicInfoListResponseDto.ofAppleMusicResponseDto(response);
    }

    public ArtistInfoListResponseDto getArtistCharts(int limit) {
        var response = appleMusicFeignClient.getAlbumCharts("albums", limit);
        return ArtistInfoListResponseDto.ofAppleMusicResponseDto(response);
    }

}
