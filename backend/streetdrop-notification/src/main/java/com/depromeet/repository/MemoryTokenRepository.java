package com.depromeet.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryTokenRepository implements TokenRepository {

    private final Map<Long, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public void save(Long userId, String token) {
        tokenMap.put(userId, token);
    }

    @Override
    public Optional<String> findByUserId(Long userId) {
        return Optional.ofNullable(tokenMap.get(userId));
    }

    @Override
    public List<String> findByUserIds(List<Long> userIds) {
        return userIds.stream()
                .map(tokenMap::get)
                .toList();
    }

    @Override
    public List<String> findAll() {
        return tokenMap.values().stream().toList();
    }

    @Override
    public void update(Long userId, String token) {
        tokenMap.put(userId, token);
    }

    @Override
    public void delete(Long userId) {
        tokenMap.remove(userId);
    }
}
