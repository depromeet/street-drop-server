package com.depromeet.domains.recommend.repository;

public interface UserReadRepository {
    void save(Long userId);
    Boolean isSent(Long userId);
}
