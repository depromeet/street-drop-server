package com.depromeet.external.feign.config;

import com.depromeet.external.feign.client.AppleMusicFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = AppleMusicFeignClient.class)
public class FeignConfig {
}
