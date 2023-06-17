package com.depromeet.common.error.handler;

import com.depromeet.common.error.dto.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorEvent {
    public ErrorCode errorCode;
    public HttpServletRequest request;
    public Exception e;
}
