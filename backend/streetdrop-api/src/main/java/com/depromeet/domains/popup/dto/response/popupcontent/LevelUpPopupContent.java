package com.depromeet.domains.popup.dto.response.popupcontent;

import com.depromeet.domains.popup.data.LevelUpPopupData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LevelUpPopupContent extends PopupContent {
    private final String title;
    private final String description;
    private final long remainCount;

    @Builder
    public LevelUpPopupContent(long id, String popupName, String title, String description, long remainCount) {
        super(id, popupName);
        this.title = title;
        this.description = description;
        this.remainCount = remainCount;
    }

    public LevelUpPopupContent(long id, LevelUpPopupData levelUpPopupData){
        super(id, levelUpPopupData.getPopupName());
        this.title = levelUpPopupData.getTitle();
        this.description = levelUpPopupData.getDescription();
        this.remainCount = levelUpPopupData.getRemainCount();
    }

    public String toType() {
        return "levelUp";
    }

}
