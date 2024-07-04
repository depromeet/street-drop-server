ALTER TABLE announcement RENAME notice;
ALTER TABLE notice CHANGE announcement_id notice_id BIGINT NOT NULL AUTO_INCREMENT;