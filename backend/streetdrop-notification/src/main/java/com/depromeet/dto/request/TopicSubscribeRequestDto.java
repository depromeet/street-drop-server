package com.depromeet.dto.request;

import com.google.firebase.database.annotations.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TopicSubscribeRequestDto {
    @NotNull
    List<Long> userIds;

    @NotNull
    String topic;
}
