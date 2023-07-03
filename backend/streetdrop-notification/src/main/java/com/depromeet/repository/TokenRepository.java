package com.depromeet.repository;

public interface TokenRepository {
    void save(Long userId, String token);
    String findByUserId(Long userId);

    void update(Long userId, String token);
}
