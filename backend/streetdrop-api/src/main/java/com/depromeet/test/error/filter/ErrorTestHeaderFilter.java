package com.depromeet.test.error.filter;

import com.depromeet.common.error.dto.ErrorCodeMapper;
import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import com.depromeet.common.error.http.dto.HttpErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Profile({"dev", "local"})
@Component
@Slf4j
public class ErrorTestHeaderFilter extends OncePerRequestFilter {

    public static final String ERROR_TEST_HEADER = "STREET-DROP-ERROR-TEST-CODE";
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String errorTestHeader = request.getHeader(ERROR_TEST_HEADER);

        if (errorTestHeader != null) {
            Optional<ErrorCode> errorCode = ErrorCodeMapper.findByErrorCode(errorTestHeader);

            if (errorCode.isPresent()) {
                throwErrorResponse(response, errorCode.get());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private <T extends ErrorCodeInterface> void throwErrorResponse(HttpServletResponse response, T errorCode) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(errorCode.getStatus().value());

        HttpErrorResponseDto httpErrorResponseDto = HttpErrorResponseDto.from(errorCode);
        String errorResponseJson = objectMapper.writeValueAsString(httpErrorResponseDto);

        response.getWriter().write(errorResponseJson);
    }

}