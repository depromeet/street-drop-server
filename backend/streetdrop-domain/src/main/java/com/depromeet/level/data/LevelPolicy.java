package com.depromeet.level.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelPolicy {
    LEVEL_1("드랍스타터", "신규유저", "https://street-drop2.s3.ap-northeast-2.amazonaws.com/USER_PROFILE/Level1.png", 1L, 0L, 5L),
    LEVEL_2("프로 드랍퍼", "5개 이상 드랍한 유저", "https://street-drop2.s3.ap-northeast-2.amazonaws.com/USER_PROFILE/Level2.png", 2L, 5L, 25L),
    LEVEL_3("스폐셜 DJ", "25개 이상 드랍한 유저", "https://street-drop2.s3.ap-northeast-2.amazonaws.com/USER_PROFILE/Level3.png", 3L, 25L, Long.MAX_VALUE);

    private final String levelName;
    private final String description;
    private final String levelImage;
    private final Long level;
    private final Long dropCountStart;
    private final Long dropCountEnd;

    public static LevelPolicy getNextLevel(LevelPolicy currentLevel){
        int nextIndex = currentLevel.ordinal() + 1;
        if (nextIndex < LevelPolicy.values().length) {
            return LevelPolicy.values()[nextIndex];
        } else {
            return currentLevel;
        }
    }
}
