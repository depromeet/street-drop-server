package com.depromeet.domains.notification.repository;

import com.depromeet.domains.notification.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
