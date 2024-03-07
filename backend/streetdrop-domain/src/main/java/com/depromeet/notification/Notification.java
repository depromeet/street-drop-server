package com.depromeet.notification;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("notification")
public class Notification {

    @Id
    private String id;
    private String title;
    private String content;
    private String type;
    private User user;
    private Target target;
    private LocalDateTime viewedTime;
    private boolean isDeleted;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date modifiedAt;

}
