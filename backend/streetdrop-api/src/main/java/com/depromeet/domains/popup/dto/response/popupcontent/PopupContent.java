package com.depromeet.domains.popup.dto.response.popupcontent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopupContent {
    private Long id;
    private String popupName;

    public String toType() {
        return "basic";
    }
}
