package com.depromeet.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryTokenRepository implements TokenRepository {

    private final Map<Long, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public void save(Long userId, String token) {
        tokenMap.put(userId, token);
    }

    @Override
    public String findByUserId(Long userId) {
        return tokenMap.get(userId);
    }

    @Override
    public void update(Long userId, String token) {
        tokenMap.put(userId, token);
    }
}
