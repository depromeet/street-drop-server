package com.depromeet.common.error.handler;

import com.depromeet.common.monitoring.MonitoringProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.depromeet.common.error.dto.ErrorCode.INTERNAL_SERVER_ERROR;

@Component
@RequiredArgsConstructor
public class ErrorEventHandler {

    private final MonitoringProvider monitoringProvider;

    @EventListener
    @Async
    public void handleErrorEvent(ErrorEvent event) {
        switch (event.errorCode) {
            case INTERNAL_SERVER_ERROR:
                monitoringProvider.sendError(INTERNAL_SERVER_ERROR.getMessage(), event.request, event.e);
                break;
            default:
                break;
        }
    }
}
