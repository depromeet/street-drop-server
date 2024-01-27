package com.depromeet.external.swagger.config;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.http.dto.HttpErrorResponseDto;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class ErrorResponseExampleCustomizer {

    public void generateErrorResponseExample(Operation operation, List<Map<ErrorCode, String>> errorCodeExampleList) {
        ApiResponses responses = operation.getResponses();

        Map<Integer, List<Map<ErrorCode, String>>> errorCodeExampleByStatus = errorCodeExampleList.stream()
                .collect(groupingBy(errorCodeStringMap -> errorCodeStringMap.keySet().iterator().next().getStatus().value()));

        errorCodeExampleByStatus
                .forEach(
                        (statusCode, value) -> {
                            MediaType mediaType = new MediaType();
                            value.forEach(map ->
                                    map.forEach(
                                            (errorResponse, description) -> {
                                                Example example = generateExample(errorResponse, description);
                                                mediaType.addExamples(errorResponse.getErrorResponseCode(), example);
                                            }
                                    ));
                            Content content = generateContent(mediaType);
                            ApiResponse apiResponse = generateApiResponse(content);
                            responses.addApiResponse(statusCode.toString(), apiResponse);
                        });
    }

    private ApiResponse generateApiResponse(Content content) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setContent(content);
        return apiResponse;
    }

    private Content generateContent(MediaType mediaType) {
        Content content = new Content();
        content.addMediaType("application/json", mediaType);
        return content;
    }

    private Example generateExample(ErrorCode errorCode, String description) {
        Example example = new Example();
        HttpErrorResponseDto httpErrorResponseDto = HttpErrorResponseDto.from(errorCode);
        example.setValue(httpErrorResponseDto);
        example.setDescription(description);
        return example;
    }
}
