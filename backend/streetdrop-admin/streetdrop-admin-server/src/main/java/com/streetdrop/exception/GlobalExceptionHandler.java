package com.streetdrop.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(HttpClientErrorException.BadRequest e) {
        return ErrorResponse.toResponseEntity(ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .status(ErrorCode.INVALID_INPUT_VALUE.getStatus().value())
                .code(ErrorCode.INVALID_INPUT_VALUE.getCode())
                .build();
        return ResponseEntity.status(ErrorCode.INVALID_INPUT_VALUE.getStatus()).body(errorResponse);
    }

    @ExceptionHandler({NoHandlerFoundException.class, Exception.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        String reqUrl = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
                .build()
                .toUriString();
        String method = request.getMethod();
        log.error("Request URL : {}, Method : {}, Exception : {}", reqUrl, method, e);
        return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}