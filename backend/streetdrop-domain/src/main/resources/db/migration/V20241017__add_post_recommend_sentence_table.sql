CREATE TABLE IF NOT EXISTS post_recommend_sentence (
   post_recommend_sentence_id BIGINT NOT NULL AUTO_INCREMENT,
   sentence VARCHAR(255) NOT NULL,
   PRIMARY KEY (post_recommend_sentence_id)
)