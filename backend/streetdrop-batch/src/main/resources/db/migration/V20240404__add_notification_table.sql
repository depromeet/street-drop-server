CREATE TABLE IF NOT EXISTS users_device (
    user_device_id BIGINT AUTO_INCREMENT,
    device_token VARCHAR(255) NOT NULL,
    alert_on BIT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY(user_device_id),
    constraint FK_usesrs_device_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS notification_action (
    notification_action_id BIGINT NOT NULL AUTO_INCREMENT,
    path VARCHAR(255),
    pid VARCHAR(255),
    PRIMARY KEY(notification_action_id)
);

CREATE TABLE IF NOT EXISTS notification (
    notification_id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(100) NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    is_viewed BIT NOT NULL,
    user_device_id BIGINT NOT NULL,
    notification_action_id BIGINT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY(notification_id),
    constraint FK_notification_users_device FOREIGN KEY (user_device_id) REFERENCES users_device(user_device_id),
    constraint FK_notification_notification_action FOREIGN KEY (notification_action_id) REFERENCES notification_action(notification_action_id)
);