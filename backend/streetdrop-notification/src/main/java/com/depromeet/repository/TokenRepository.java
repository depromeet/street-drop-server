package com.depromeet.repository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
    void save(Long userId, String token);

    Optional<String> findByUserId(Long userId);

    List<String> findByUserIds(List<Long> userIds);

    List<String> findAll();

    void update(Long userId, String token);

    void delete(Long userId);
}
