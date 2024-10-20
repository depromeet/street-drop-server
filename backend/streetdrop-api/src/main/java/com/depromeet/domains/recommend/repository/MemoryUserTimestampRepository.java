package com.depromeet.domains.recommend.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryUserTimestampRepository implements UserTimestampRepository{

    private static Map<Long, LocalDateTime> store = new HashMap<>();

    @Override
    public void save(Long userId) {
        store.put(userId, LocalDateTime.now());
    }

    @Override
    public Boolean isSent(Long userId) {
        System.out.println(">> " + store);
        return Optional.ofNullable(store.get(userId))
                .map(readTime -> !readTime.isBefore(LocalDateTime.now().minusDays(1)))
                .orElse(false);
    }

}
