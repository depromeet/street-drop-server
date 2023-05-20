package com.depromeet.streetdrop.global.error;

import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.dto.ErrorResponseDto;
import com.depromeet.streetdrop.global.error.exception.common.BusinessException;
import com.depromeet.streetdrop.global.error.exception.common.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
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
}

