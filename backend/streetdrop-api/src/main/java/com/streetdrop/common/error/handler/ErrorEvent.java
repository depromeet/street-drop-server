package com.streetdrop.common.error.handler;

import com.streetdrop.common.error.dto.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorEvent {
    public ErrorCode errorCode;
    public HttpServletRequest request;
    public Exception e;
}
