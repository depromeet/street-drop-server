package com.depromeet.domains.recommend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommendType {
    POPULAR_CHART_SONG("지금 인기 있는 음악", "basic", "애플 뮤직의 '지금 인기 있는 곡' 리스트를 반영했어요.", 30, true),
    RECENT_SONGS("최근 드랍된 음악", "basic", "", 15, true),
    CHART_ARTIST("아티스트", "keyword", "", 10, false);

    private final String title;
    private final String type;
    private final String description;
    private final int limit;
    private final boolean nextPage;
}
