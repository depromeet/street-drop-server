package com.depromeet.logging.filter;

import com.depromeet.logging.dto.HttpRequestLogInfo;
import com.depromeet.logging.dto.HttpResponseLogInfo;
import com.depromeet.logging.filter.wrapper.RequestWrapper;
import com.depromeet.logging.filter.wrapper.ResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("traceId", UUID.randomUUID().toString());
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        }
        MDC.clear();
    }

    protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        try {
            logRequest(request);
            filterChain.doFilter(request, response);
        } finally {
            logResponse(response);
            response.copyBodyToResponse();
        }
    }

    private static void logRequest(RequestWrapper request) {
        HttpRequestLogInfo httpLogInfo = new HttpRequestLogInfo(request);
        log.info(httpLogInfo.toJson());
    }

    private static void logResponse(ContentCachingResponseWrapper response) throws IOException {
        HttpResponseLogInfo httpLogInfo = new HttpResponseLogInfo(response);
        log.info(httpLogInfo.toJson());
    }
}

