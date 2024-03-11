package com.depromeet.domains.popup.dto.response;

import com.depromeet.domains.popup.dto.response.popupcontent.PopupContent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class PopupDataDto<T extends PopupContent> {
    @Schema(description = "팝업 타입", example = "levelUp")
    public String type;
    @Schema(description = "팝업 내용")
    public T content;

    public PopupDataDto(T popupCotent) {
        this.type = popupCotent.toType();
        this.content = popupCotent;
    }
}
