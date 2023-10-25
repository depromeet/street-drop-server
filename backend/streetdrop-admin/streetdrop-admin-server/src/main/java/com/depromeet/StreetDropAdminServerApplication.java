package com.depromeet;

import com.depromeet.auth.event.CreateAdministratorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreetDropAdminServerApplication {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(StreetDropAdminServerApplication.class, args);
    }


    @Bean
    public CommandLineRunner run() {
        return (String[] args) -> {
            eventPublisher.publishEvent(new CreateAdministratorEvent());
        };
    }
}