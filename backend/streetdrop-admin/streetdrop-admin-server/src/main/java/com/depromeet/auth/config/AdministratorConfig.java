package com.depromeet.auth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdministratorConfig {

    @Value("${admin-member.id}")
    private String id;

    @Value("${admin-member.password}")
    private String password;

}
