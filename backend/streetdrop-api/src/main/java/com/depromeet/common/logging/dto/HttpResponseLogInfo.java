package com.depromeet.common.logging.dto;

import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class HttpResponseLogInfo {
    public String traceId;
    public String responseBody;
    public Integer responseStatus;

    public HttpResponseLogInfo(ContentCachingResponseWrapper response) throws IOException {
        this.traceId = MDC.get("traceId");
        this.responseBody = getContent(response.getContentType(), response.getContentInputStream());
        this.responseStatus = response.getStatus();
    }

    public String toJson() {
        return "{" +
                "\"traceId\":\"" + traceId + "\"," +
                "\"responseBody\":\"" + responseBody + "\"," +
                "\"responseStatus\":" + responseStatus +
                "}";
    }

    private String getContent(String contentType, InputStream inputStream) throws IOException {
        boolean visible = isVisible(MediaType.valueOf(contentType == null ? "application/json" : contentType));
        if (visible) {
            byte[] content = StreamUtils.copyToByteArray(inputStream);
            if (content.length > 0) {
                return new String(content, 0, Math.min(content.length, 5120));
            } else {
                return "";
            }
        } else {
            return "BINARY";
        }
    }

    private static boolean isVisible(MediaType mediaType) {
        final List<MediaType> VISIBLE_TYPES = Arrays.asList(
                MediaType.valueOf("text/*"),
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.valueOf("application/*+json"),
                MediaType.valueOf("application/*+xml"),
                MediaType.MULTIPART_FORM_DATA
        );

        return VISIBLE_TYPES.stream()
                .anyMatch(visibleType -> visibleType.includes(mediaType));
    }
}