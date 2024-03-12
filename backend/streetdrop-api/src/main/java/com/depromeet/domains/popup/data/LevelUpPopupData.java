package com.depromeet.domains.popup.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelUpPopupData {
    LEVEL_2("LEVEL_2", "축하합니다!\n프로드랍퍼가 되었어요", "프로드랍퍼는 전보다 200M 넓게 음악을 들을 수 있어요", 20L),
    LEVEL_3("LEVEL_3", "축하합니다!\n스폐셜DJ가 되었어요", "스폐셜DJ는 전보다 200M 넓게 음악을 들을 수 있어요", 0L);

    private final String popupName;
    private final String title;
    private final String description;
    private final Long remainCount;
}
