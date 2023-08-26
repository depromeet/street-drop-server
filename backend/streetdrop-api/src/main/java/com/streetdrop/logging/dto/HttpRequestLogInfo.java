package com.streetdrop.logging.dto;

import com.streetdrop.logging.filter.wrapper.RequestWrapper;
import org.slf4j.MDC;

public class HttpRequestLogInfo {
    public String traceId;
    public String requestMethod;
    public String requestUri;
    public String xAmznTraceId;
    public String xSdpIdfv;
    public String userAgent;

    public HttpRequestLogInfo(RequestWrapper requestWrapper) {
        String queryString = requestWrapper.getQueryString();
        this.traceId = MDC.get("traceId");
        this.requestMethod = requestWrapper.getMethod();
        this.requestUri = requestWrapper.getRequestURI() + (queryString == null ? "" : "?" + queryString);
        this.xAmznTraceId = requestWrapper.getHeader("x-amzn-trace-id");
        this.xSdpIdfv = requestWrapper.getHeader("x-sdp-idfv");
        this.userAgent = requestWrapper.getHeader("user-agent");
    }

    public String toJson() {
        return "{" +
                "\"traceId\":\"" + traceId + "\"," +
                "\"requestMethod\":\"" + requestMethod + "\"," +
                "\"requestUri\":\"" + requestUri + "\"," +
                "\"xAmznTraceId\":\"" + xAmznTraceId + "\"," +
                "\"xSdpIdfv\":\"" + xSdpIdfv + "\"," +
                "\"userAgent\":\"" + userAgent + "\"" +
                "}";
    }
}


