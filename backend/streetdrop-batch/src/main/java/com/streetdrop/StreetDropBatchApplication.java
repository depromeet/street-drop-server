package com.streetdrop;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class StreetDropBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreetDropBatchApplication.class, args);
    }
}