package com.depromeet.domains.notification.dto.request;

import com.google.firebase.database.annotations.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TopicSubscribeRequestDto {
    @NotNull
    List<Long> userIds;

    @NotNull
    String topic;
}
