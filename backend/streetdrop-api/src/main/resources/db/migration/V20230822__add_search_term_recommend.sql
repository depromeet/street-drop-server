CREATE TABLE search_recommend_term (
    search_recommend_term_id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description JSON NOT NULL,
    terms JSON NOT NULL,
    PRIMARY KEY (search_recommend_term_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;