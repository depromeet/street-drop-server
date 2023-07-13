package com.depromeet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StreetDropApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreetDropApiApplication.class, args);
    }

}