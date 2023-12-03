package com.depromeet.external.swagger.config;

import com.depromeet.common.error.dto.ErrorCodeMapper;
import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.external.swagger.annotation.ApiErrorResponses;
import io.swagger.v3.oas.models.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Component
@Slf4j
@AllArgsConstructor
public class CustomOperationCustomizer implements OperationCustomizer {

    private final ErrorRequestHeaderCustomizer errorRequestHeaderCustomizer;
    private final ErrorResponseExampleCustomizer errorResponseExampleCustomizer;

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        ApiErrorResponse apiErrorResponseAnnotation = handlerMethod.getMethodAnnotation(ApiErrorResponse.class);
        ApiErrorResponses apiErrorResponsesAnnotation = handlerMethod.getMethodAnnotation(ApiErrorResponses.class);

        if (apiErrorResponseAnnotation != null) {
            handleApiErrorResponse(operation, apiErrorResponseAnnotation);
        }

        if (apiErrorResponsesAnnotation != null) {
            handleApiErrorResponses(operation, apiErrorResponsesAnnotation);
        }

        return operation;
    }

    private void handleApiErrorResponse(Operation operation, ApiErrorResponse apiErrorResponseAnnotation) {
        ErrorCodeMapper.findByErrorCode(apiErrorResponseAnnotation.errorCode())
                .ifPresent(errorCode -> {
                    var errorCodeExampleList = List.of(Map.of(errorCode, apiErrorResponseAnnotation.description()));
                    errorResponseExampleCustomizer.generateErrorResponseExample(operation, errorCodeExampleList);
                    errorRequestHeaderCustomizer.generateErrorRequestHeader(operation, errorCode);
                });
    }

    private void handleApiErrorResponses(Operation operation, ApiErrorResponses apiErrorResponsesAnnotation) {
        List<Map<ErrorCode, String>> errorCodeExampleList = Arrays.stream(apiErrorResponsesAnnotation.value())
                .map(apiErrorResponse -> ErrorCodeMapper.findByErrorCode(apiErrorResponse.errorCode())
                        .map(code -> Map.of(code, apiErrorResponse.description()))
                        .orElse(emptyMap()))
                .filter(map -> !map.isEmpty())
                .toList();

        List<ErrorCode> resultList = errorCodeExampleList.stream().flatMap(map -> map.keySet().stream()).toList();

        errorRequestHeaderCustomizer.generateErrorRequestHeader(operation, resultList);
        errorResponseExampleCustomizer.generateErrorResponseExample(operation, errorCodeExampleList);
    }

}
