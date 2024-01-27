package com.depromeet.common.error;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.event.ErrorEvent;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.common.error.http.dto.HttpErrorResponseDto;
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
import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final ApplicationEventPublisher eventPublisher;

	@ExceptionHandler(HttpClientErrorException.Forbidden.class)
	protected ResponseEntity<HttpErrorResponseDto> handleForbiddenException(
			final HttpClientErrorException.Forbidden e,
			final HttpServletRequest request
	) {
		return HttpErrorResponseDto.toResponseEntity(CommonErrorCode.FORBIDDEN);
	}

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<HttpErrorResponseDto> handleBusinessException(
			final BusinessException e,
			final HttpServletRequest request
	) {
		log.error("BusinessException: {} {}", e.getErrorCode(), request.getRequestURL());
		return HttpErrorResponseDto.toResponseEntity(e.getErrorCode());
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<HttpErrorResponseDto> handleNotFoundException(
			final NotFoundException e
	) {
		return HttpErrorResponseDto.toResponseEntity(e.getErrorCode());
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<HttpErrorResponseDto> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e,
            final HttpServletRequest request
    ) {
        log.error("MethodArgumentNotValidException: {} {}", e.getMessage(), request.getRequestURL());
		HttpErrorResponseDto httpErrorResponseDto = HttpErrorResponseDto.builder()
				.errorResponseCode(CommonErrorCode.METHOD_ARGUMENT_NOT_VALID.getErrorResponseCode())
				.status(CommonErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus().value())
				.title(CommonErrorCode.METHOD_ARGUMENT_NOT_VALID.getTitle())
                .message(e.getAllErrors().get(0).getDefaultMessage()).build();

		return ResponseEntity
				.status(CommonErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus().value())
				.body(httpErrorResponseDto);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<HttpErrorResponseDto> handleMethodNotSupportException() {
		return HttpErrorResponseDto.toResponseEntity(CommonErrorCode.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(value = {Exception.class, RuntimeException.class, SQLException.class, DataIntegrityViolationException.class})
	protected ResponseEntity<HttpErrorResponseDto> handleInternalException(
			final Exception e,
			final HttpServletRequest request
	) {
		log.error("Exception: {} {}", e.getMessage(), request.getRequestURL());
		eventPublisher.publishEvent(new ErrorEvent<>(CommonErrorCode.INTERNAL_SERVER_ERROR, request, e));
		return HttpErrorResponseDto.toResponseEntity(CommonErrorCode.INTERNAL_SERVER_ERROR);
	}

}

