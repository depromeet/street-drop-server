package com.depromeet.test.delay;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Profile({"test", "local"})
@Component
@Slf4j
public class DelayTestFilter extends OncePerRequestFilter {

    public static final String DELAY_TIME_HEADER = "STREET-DROP-RESPONSE-DELAY-TIME";
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String errorTestCode = request.getHeader(DELAY_TIME_HEADER);
        if (errorTestCode != null) {
            try {
                int delayTime = Integer.parseInt(errorTestCode);
                Thread.sleep(delayTime);
            } catch (NumberFormatException | InterruptedException ignored) {
            }
            filterChain.doFilter(request, response);
        }
    }

}