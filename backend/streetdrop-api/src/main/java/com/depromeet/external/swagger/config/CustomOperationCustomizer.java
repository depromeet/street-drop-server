package com.depromeet.external.swagger.config;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.dto.ErrorResponseDto;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.external.swagger.annotation.ApiErrorResponses;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class CustomOperationCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        ApiErrorResponse apiErrorResponseAnnotation =
                handlerMethod.getMethodAnnotation(ApiErrorResponse.class);
        ApiErrorResponses apiErrorResponsesAnnotation =
                handlerMethod.getMethodAnnotation(ApiErrorResponses.class);
        if (apiErrorResponseAnnotation != null) {
            generateErrorResponseExample(operation, apiErrorResponseAnnotation.errorCode(), apiErrorResponseAnnotation.description());
        }
        if (apiErrorResponsesAnnotation != null) {
            Arrays.stream(apiErrorResponsesAnnotation.value())
                    .forEach(apiErrorResponse ->
                            generateErrorResponseExample(operation, apiErrorResponse.errorCode(), apiErrorResponse.description()));
        }
        return operation;
    }


    private void generateErrorResponseExample(Operation operation, ErrorCode type, String description) {
        ApiResponses responses = operation.getResponses();

        ErrorCode[] errorCodes = {type};

        Map<Integer, List<Map<ErrorResponseDto, String>>> httpErrorCodeToErrorResponseExample =
                Arrays.stream(errorCodes)
                        .map(
                                errorCode -> {
                                    ErrorResponseDto errorResponseDto = new ErrorResponseDto(errorCode);
                                    return Map.of(errorResponseDto, description);
                                }
                        )
                        .collect(groupingBy(errorResponseDtoStringMap -> errorResponseDtoStringMap.keySet().iterator().next().getStatus()));

        addExamplesToResponses(responses, httpErrorCodeToErrorResponseExample);
    }

    private void addExamplesToResponses(
            ApiResponses responses,
            Map<Integer, List<Map<ErrorResponseDto, String>>> httpErrorCodeToErrorResponseExample
    ) {
        httpErrorCodeToErrorResponseExample.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();
                    v.forEach(
                            map -> map.forEach(
                                    (errorResponse, description) -> {
                                        mediaType.addExamples(
                                                errorResponse.getCode(),
                                                generateErrorResponseExample(errorResponse, description)
                                        );
                                    }
                            ));
                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(status.toString(), apiResponse);
                });
    }

    private Example generateErrorResponseExample(ErrorResponseDto errorResponse, String description) {
        Example example = new Example();
        example.setValue(errorResponse);
        example.setDescription(description);
        return example;
    }
}
