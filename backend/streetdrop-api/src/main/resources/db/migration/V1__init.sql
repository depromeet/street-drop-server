CREATE TABLE IF NOT EXISTS `album` (
    `album_id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `album_cover_id` bigint NOT NULL,
    `artist_id` bigint NOT NULL,
    PRIMARY KEY (`album_id`),
    KEY `FK7otyksnaj781l0b2r8570vefc` (`album_cover_id`),
    KEY `FKmwc4fyyxb6tfi0qba26gcf8s1` (`artist_id`),
    CONSTRAINT `FK7otyksnaj781l0b2r8570vefc` FOREIGN KEY (`album_cover_id`) REFERENCES `album_cover` (`album_cover_id`),
    CONSTRAINT `FKmwc4fyyxb6tfi0qba26gcf8s1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`artist_id`)
)ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `album_cover` (
    `album_cover_id` bigint NOT NULL AUTO_INCREMENT,
    `album_image` varchar(255) NOT NULL,
    `album_thumbnail` varchar(255) NOT NULL,
    PRIMARY KEY (`album_cover_id`)
) ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `artist` (
    `artist_id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=355 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `block_user` (
    `block_id` bigint NOT NULL AUTO_INCREMENT,
    `blocked_id` bigint NOT NULL,
    `blocker_id` bigint NOT NULL,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    PRIMARY KEY (`block_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `city_area` (
    `city_id` bigint NOT NULL AUTO_INCREMENT,
    `city_code` int NOT NULL,
    `city_name` varchar(255) NOT NULL,
    `version` int NOT NULL,
    `state_id` bigint NOT NULL,
    PRIMARY KEY (`city_id`),
    KEY `FKrf563rtlt9al5bw1lekn18du1` (`state_id`),
    CONSTRAINT `FKrf563rtlt9al5bw1lekn18du1` FOREIGN KEY (`state_id`) REFERENCES `state_area` (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `default_nick_name` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `post_nick_name` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
    `pre_nick_name` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `genre` (
    `genre_id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `item` (
    `item_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `album_cover_id` bigint NOT NULL,
    `song_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`item_id`),
    KEY `FKip7vjlqnw0rt125paig31ni3` (`album_cover_id`),
    KEY `FKf9j8ocdanjs7a48djb3aqd0en` (`song_id`),
    CONSTRAINT `FKf9j8ocdanjs7a48djb3aqd0en` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
    CONSTRAINT `FKip7vjlqnw0rt125paig31ni3` FOREIGN KEY (`album_cover_id`) REFERENCES `album_cover` (`album_cover_id`)
) ENGINE=InnoDB AUTO_INCREMENT=798 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `item_claim` (
    `item_claim_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    `item_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    `reason` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`item_claim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `item_like` (
    `item_like_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    `item_id` bigint DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`item_like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `item_location` (
    `item_location_id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    `point` point NOT NULL,
    `item_id` bigint NOT NULL,
    `village_id` bigint NOT NULL,
    PRIMARY KEY (`item_location_id`),
    KEY `FK50oxdirrbr9uk55y7xlrq6jmv` (`item_id`),
    KEY `FKlnnflryk5f1y35ismb0qpnepy` (`village_id`),
    CONSTRAINT `FK50oxdirrbr9uk55y7xlrq6jmv` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
    CONSTRAINT `FKlnnflryk5f1y35ismb0qpnepy` FOREIGN KEY (`village_id`) REFERENCES `village_area` (`village_id`)
) ENGINE=InnoDB AUTO_INCREMENT=798 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `song` (
    `song_id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `album_id` bigint NOT NULL,
    PRIMARY KEY (`song_id`),
    KEY `FKrcjmk41yqj3pl3iyii40niab0` (`album_id`),
    CONSTRAINT `FKrcjmk41yqj3pl3iyii40niab0` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=535 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `song_genre` (
    `song_genre_id` bigint NOT NULL AUTO_INCREMENT,
    `genre_id` bigint NOT NULL,
    `song_id` bigint DEFAULT NULL,
    PRIMARY KEY (`song_genre_id`),
    KEY `FKmpuht870e976moxtxywrfngcr` (`genre_id`),
    KEY `FK1ssu87dg5vsdxpmyjqqc42if3` (`song_id`),
    CONSTRAINT `FK1ssu87dg5vsdxpmyjqqc42if3` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
    CONSTRAINT `FKmpuht870e976moxtxywrfngcr` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2485 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `state_area` (
    `state_id` bigint NOT NULL AUTO_INCREMENT,
    `state_code` int NOT NULL,
    `state_name` varchar(255) NOT NULL,
    `version` int NOT NULL,
    PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `users` (
    `user_id` bigint NOT NULL AUTO_INCREMENT,
    `idfv` varchar(100) NOT NULL,
    `nickname` varchar(20) NOT NULL,
    `music_app` varchar(255) NOT NULL,
    `user_level_id` bigint NOT NULL,
    `created_at` datetime(6) NOT NULL,
    `modified_at` datetime(6) NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `users_level` (
    `user_level_id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`user_level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `village_area` (
    `village_id` bigint NOT NULL AUTO_INCREMENT,
    `version` int NOT NULL,
    `village_center_point` point DEFAULT NULL,
    `village_code` int NOT NULL,
    `village_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `village_polygon` multipolygon NOT NULL,
    `city_id` bigint NOT NULL,
    PRIMARY KEY (`village_id`),
    KEY `FKadtch2u4brlxykg1e03xdkyiw` (`city_id`),
    SPATIAL KEY `idx_coordinates` (`village_polygon`),
    CONSTRAINT `FKadtch2u4brlxykg1e03xdkyiw` FOREIGN KEY (`city_id`) REFERENCES `city_area` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3516 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;