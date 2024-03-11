package com.depromeet.domains.popup.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuidePopupData {
    GUIDE_1("GUIDE_1", "더 많은 음악을 듣고 싶다면?", "레벨업하면 음악을 들을 수 있는 반경이 200M 더 넓어져요!");

    private final String popupName;
    private final String title;
    private final String description;
}
