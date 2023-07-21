package com.depromeet.global.security.provider;

import com.depromeet.exception.ErrorCode;
import com.depromeet.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import java.io.IOException;

@Slf4j
public class InvalidTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("Invalid Token - Url: {}, Message: {}", request.getRequestURI(), authException.getMessage());
        response.setContentType("application/json");
        response.setStatus(ErrorCode.INVALID_TOKEN.getStatus().value());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        response.getWriter().write(mapper.writeValueAsString(
                ErrorResponse.builder()
                        .status(ErrorCode.INVALID_TOKEN.getStatus().value())
                        .code(ErrorCode.INVALID_TOKEN.getCode())
                        .message(ErrorCode.INVALID_TOKEN.getMessage())
                        .build())
        );
    }
}
