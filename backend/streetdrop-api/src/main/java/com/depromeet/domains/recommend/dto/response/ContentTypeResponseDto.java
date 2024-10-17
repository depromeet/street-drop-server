package com.depromeet.domains.recommend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ContentTypeResponseDto {

    @Getter
    @AllArgsConstructor
    public static class BasicTypeResponseDto {
        List<MusicInfoResponseDto> basic;
    }

    @Getter
    @AllArgsConstructor
    public static class KeywordTypeResponseDto {
        List<ArtistInfoResponseDto> keyword;
    }
}
