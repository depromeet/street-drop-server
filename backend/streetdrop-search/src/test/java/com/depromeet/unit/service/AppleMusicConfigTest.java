package com.depromeet.unit.service;

import com.depromeet.music.service.AppleMusicConfig;
import com.depromeet.music.service.AppleMusicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.*;

public class AppleMusicConfigTest {

    // 여기서는 AppleMusicConfig의 AppleMusicApiKey에 대해서 다중 쓰레드가 접근했을때의 동시성을 확인합니다
    // 이를 위해 AppleMusicConfig의 AppleMusicApiKey를 2개의 쓰레드가 동시에 접근하도록 합니다



    // 여기서는 AppleMusicConfig의 AppleMusicApiKey에 대해서 다중 쓰레드가 접근했을때의 동시성을 확인합니다



    @Test
    public void testAppleMusicApiKey() {

        AppleMusicConfig appleMusicConfig = new AppleMusicConfig();
        class FakeAppleMusicService {
            private AppleMusicConfig appleMusicConfig;

            public FakeAppleMusicService(AppleMusicConfig appleMusicConfig) {
                this.appleMusicConfig = appleMusicConfig;
            }
            public void searchMusic(String keyword) {
                var key = appleMusicConfig.getAppleMusicApiKey();
                System.out.println(key);
            }
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 다중 쓰레드가 동시에 접근함
        Runnable runnable = () -> {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) threadPool;
            System.out.println("현재 쓰레드 풀의 쓰레드 수: " + threadPoolExecutor.getPoolSize());
            // 현재 쓰레드
            System.out.println("현재 쓰레드: " + Thread.currentThread().getName());

            FakeAppleMusicService fakeAppleMusicService = new FakeAppleMusicService(appleMusicConfig);
            fakeAppleMusicService.searchMusic("test");

        };

        threadPool.execute(runnable);

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // 예외 처리
        }

    }

}
