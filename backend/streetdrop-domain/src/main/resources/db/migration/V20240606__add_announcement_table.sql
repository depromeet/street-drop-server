CREATE TABLE IF NOT EXISTS announcement (
    announcement_id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(3000) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL
)