package com.depromeet.domains.recommend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommendType {
    POPULAR_CHART_SONG("지금 인기 있는 음악", 30, true),
    RECENT_SONGS("최근 드랍된 음악", 15, true),
    CHART_ARTIST("아티스트", 10, false);

    private final String title;
    private final int limit;
    private final boolean nextPage;
}
