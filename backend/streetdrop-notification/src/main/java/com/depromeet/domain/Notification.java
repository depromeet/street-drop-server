package com.depromeet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Document("notification")
public class Notification {
    @Id
    private Long id;
    private String title;
    private String content;
    private String type;
    private User user;
    private Target target;
    private LocalDateTime viewedTime;
    private boolean isDeleted;
}