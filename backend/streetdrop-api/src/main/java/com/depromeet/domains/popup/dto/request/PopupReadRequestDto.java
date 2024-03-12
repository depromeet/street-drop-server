package com.depromeet.domains.popup.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PopupReadRequestDto {
    private final String type;

    private final long id;
}
