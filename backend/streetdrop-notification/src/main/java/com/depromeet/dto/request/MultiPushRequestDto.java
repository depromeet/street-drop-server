package com.depromeet.dto.request;

import com.google.firebase.database.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MultiPushRequestDto {
    @NotNull
    private List<Long> userIds;

    private String title;

    @NotNull
    private String content;
}
