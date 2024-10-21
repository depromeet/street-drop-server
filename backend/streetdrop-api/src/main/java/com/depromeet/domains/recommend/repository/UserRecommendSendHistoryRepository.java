package com.depromeet.domains.recommend.repository;

public interface UserRecommendSendHistoryRepository {
    void save(Long userId);
    Boolean isSent(Long userId);
}
