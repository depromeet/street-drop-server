package com.depromeet.common.error.handler;

import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import com.depromeet.common.error.event.ErrorEvent;
import com.depromeet.monitoring.MonitoringProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.depromeet.common.error.dto.CommonErrorCode.INTERNAL_SERVER_ERROR;

@Component
@RequiredArgsConstructor
public class ErrorEventHandler<T extends ErrorCodeInterface> {

    private final MonitoringProvider monitoringProvider;

    @EventListener
    @Async
    public void handleErrorEvent(ErrorEvent<T> event) {
        switch (event.getErrorCode().getStatus()) {
            case INTERNAL_SERVER_ERROR:
                monitoringProvider.sendError(INTERNAL_SERVER_ERROR.getMessage(), event.request, event.e);
                break;
            default:
                break;
        }
    }
}
