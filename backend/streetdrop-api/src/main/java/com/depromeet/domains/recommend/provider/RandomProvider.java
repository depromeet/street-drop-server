package com.depromeet.domains.recommend.provider;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public final class RandomProvider {

    private static final Random RANDOM = new Random();

    public static <T> T getRandomElement(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    private RandomProvider() {
    }

}
