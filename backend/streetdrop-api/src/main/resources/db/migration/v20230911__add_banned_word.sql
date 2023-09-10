CREATE TABLE banned_word (
    banned_word_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(20) NOT NULL
);

CREATE INDEX idx__banned_word_word ON banned_word (word);