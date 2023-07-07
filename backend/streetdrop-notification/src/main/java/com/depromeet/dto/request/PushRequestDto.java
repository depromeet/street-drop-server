package com.depromeet.dto.request;

import com.google.firebase.database.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PushRequestDto {
    @NotNull
    private Long userId;

    private String title;

    @NotNull
    private String content;
}
