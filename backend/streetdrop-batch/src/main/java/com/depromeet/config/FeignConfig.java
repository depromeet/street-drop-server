package com.depromeet.config;

import com.depromeet.external.feign.client.OpenApiFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = OpenApiFeignClient.class)
public class FeignConfig {

}
