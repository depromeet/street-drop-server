package com.depromeet.monitoring;
import jakarta.servlet.http.HttpServletRequest;

public interface MonitoringProvider {
    void sendError(String title, HttpServletRequest request, Exception e);
}
