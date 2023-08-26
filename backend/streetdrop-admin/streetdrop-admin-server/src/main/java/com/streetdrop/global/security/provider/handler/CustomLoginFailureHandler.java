package com.streetdrop.global.security.provider.handler;

import com.streetdrop.exception.ErrorCode;
import com.streetdrop.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setStatus(ErrorCode.INVALID_ID_OR_PASSWORD.getStatus().value());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        response.getWriter().write(
                mapper.writeValueAsString(
                        ErrorResponse.builder()
                                .status(ErrorCode.INVALID_ID_OR_PASSWORD.getStatus().value())
                                .code(ErrorCode.INVALID_ID_OR_PASSWORD.getCode())
                                .message(ErrorCode.INVALID_ID_OR_PASSWORD.getMessage())
                                .build()
                )
        );
    }
}
