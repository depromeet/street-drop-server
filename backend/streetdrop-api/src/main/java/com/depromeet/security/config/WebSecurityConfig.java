package com.depromeet.security.config;

import com.depromeet.domains.user.service.UserService;
import com.depromeet.security.filter.IdfvAuthenticationFilter;
import com.depromeet.security.provider.IdfvUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
                .and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/users/*").authenticated()
                .requestMatchers(HttpMethod.POST, "/items/*").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/items/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/items/*").authenticated()
                .requestMatchers(HttpMethod.POST, "notifications/tokens").authenticated()
                .anyRequest().permitAll().and()
                .anonymous().and()
                .formLogin().disable()
                .apply(new IdfvFilter());
        return http.build();
    }


    public class IdfvFilter extends AbstractHttpConfigurer<IdfvFilter, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            IdfvUserDetailsService userDetailsService = new IdfvUserDetailsService(userService);
            IdfvAuthenticationFilter filter = new IdfvAuthenticationFilter(userDetailsService);
            http.addFilterBefore(filter, BasicAuthenticationFilter.class);
        }
    }

}
