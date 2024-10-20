package com.depromeet.domains.recommend.repository;

public interface UserTimestampRepository {
    void save(Long userId);
    Boolean isSent(Long userId);
}
