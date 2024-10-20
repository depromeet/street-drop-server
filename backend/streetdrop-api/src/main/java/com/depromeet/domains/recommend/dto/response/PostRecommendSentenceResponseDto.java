package com.depromeet.domains.recommend.dto.response;

public record PostRecommendSentenceResponseDto(
        String sentence
) {
    public static PostRecommendSentenceResponseDto empty() {
        return new PostRecommendSentenceResponseDto(null);
    }
}
