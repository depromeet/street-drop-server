package com.depromeet.domains.popup.dto.response.popupcontent;

import com.depromeet.domains.popup.data.GuidePopupData;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GuidePopupContent extends PopupContent {
    private final String title;
    private final String description;

    @Builder
    public GuidePopupContent(Long id, String popupName, String title, String description) {
        super(id, popupName);
        this.title = title;
        this.description = description;
    }

    @Builder
    public GuidePopupContent(Long id, GuidePopupData guidePopUpData) {
        super(id, guidePopUpData.getPopupName());
        this.title = guidePopUpData.getTitle();
        this.description = guidePopUpData.getDescription();
    }

    public String toType() {
        return "guide";
    }


}
