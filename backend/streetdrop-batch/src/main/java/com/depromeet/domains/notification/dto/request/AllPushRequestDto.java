package com.depromeet.domains.notification.dto.request;

import com.google.firebase.database.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllPushRequestDto {
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String path;

    @NotNull
    private String pid;
}
