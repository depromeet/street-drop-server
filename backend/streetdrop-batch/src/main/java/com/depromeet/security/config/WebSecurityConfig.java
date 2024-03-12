package com.depromeet.security.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.depromeet.security.filter.ApiKeyAuthFilter;
import com.depromeet.security.provider.ApiKeyAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and().cors()
                .and().csrf().disable()
                .addFilter(apiKeyAuthFilter())
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and().formLogin().disable();
        return http.build();
    }

    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        return new ApiKeyAuthFilter(apiKeyAuthProvider());
    }

    @Bean
    public ApiKeyAuthProvider apiKeyAuthProvider() {
        return new ApiKeyAuthProvider();
    }

}
