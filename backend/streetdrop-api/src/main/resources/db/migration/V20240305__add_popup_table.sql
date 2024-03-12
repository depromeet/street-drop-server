CREATE TABLE IF NOT EXISTS `popup` (
    `popup_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `popup_name` VARCHAR(255) NOT NULL,
    `is_read` boolean NOT NULL,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    PRIMARY KEY (`popup_id`)
);