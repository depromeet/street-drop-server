package com.depromeet.common.error.event;

import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class  ErrorEvent<T extends ErrorCodeInterface> {
    public T errorCode;
    public HttpServletRequest request;
    public Exception e;
}
