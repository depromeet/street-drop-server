package com.streetdrop.common.error;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.dto.ErrorResponseDto;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.common.error.exception.common.NotFoundException;
import com.streetdrop.common.error.handler.ErrorEvent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final ApplicationEventPublisher eventPublisher;

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponseDto> handleBusinessException(
			final BusinessException e,
			final HttpServletRequest request
	) {
		log.error("BusinessException: {} {}", e.getErrorCode(), request.getRequestURL());
		return ResponseEntity
				.status(e.getErrorCode().getStatus().value())
				.body(new ErrorResponseDto(e.getErrorCode()));
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<ErrorResponseDto> handleNotFoundException(
			final NotFoundException e,
			final HttpServletRequest request) {

		log.error("MemberNotFoundException: {} {}", e.getErrorCode(), request.getRequestURL());
		return ResponseEntity
				.status(e.getErrorCode().getStatus().value())
				.body(new ErrorResponseDto(e.getErrorCode()));
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e,
            final HttpServletRequest request
    ) {
        log.error("MethodArgumentNotValidException: {} {}", e.getMessage(), request.getRequestURL());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .code(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode())
                .status(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus().value())
                .error(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus().name())
                .message(e.getAllErrors().get(0).getDefaultMessage()).build();

		return ResponseEntity
				.status(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus().value())
				.body(errorResponseDto);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponseDto> handleMethodNotSupportException(
			final HttpRequestMethodNotSupportedException e
	) {
		ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
				.code(ErrorCode.METHOD_NOT_ALLOWED.getCode())
				.status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
				.error(ErrorCode.METHOD_NOT_ALLOWED.getStatus().name())
				.message(e.getMessage()).build();

		return ResponseEntity
				.status(ErrorCode.METHOD_NOT_ALLOWED.getStatus().value())
				.body(errorResponseDto);
	}

	@ExceptionHandler(value = {Exception.class, RuntimeException.class, SQLException.class, DataIntegrityViolationException.class})
	protected ResponseEntity<ErrorResponseDto> handleInternalException(
			final Exception e,
			final HttpServletRequest request
	) {
		log.error("Exception: {} {}", e.getMessage(), request.getRequestURL());
		eventPublisher.publishEvent(new ErrorEvent(ErrorCode.INTERNAL_SERVER_ERROR, request, e));
		return ResponseEntity
				.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
				.body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR));
	}

}

