package com.depromeet.domains.notification.dto.request;

import com.depromeet.notification.vo.NotificationType;
import com.google.firebase.database.annotations.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PushRequestDto {
    @NotNull
    private List<Long> userIds;

    private String title;

    @NotNull
    private String content;

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private String path;

    @NotNull
    private String pid;
}
