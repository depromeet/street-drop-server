package com.streetdrop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON-001", "Bad Request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-002", "Not Found the Contents"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-003", "Internal Server Error"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON-004", "Invalid Input Value"),

    // Auth
    PHONE_NUMBER_OR_PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "AUTH-0001", "Phone Number Or Password Not Matched"),
    AUTH_CODE_RETRY_TIME(HttpStatus.BAD_REQUEST, "AUTH-0002", "Auth Code Retry Time Exceeded"),
    NOT_MATCH_AUTH_CODE(HttpStatus.BAD_REQUEST, "AUTH-0003", "Not Match Auth Code"),
    AUTH_CODE_SEND_REQUIRED(HttpStatus.BAD_REQUEST, "AUTH-0004", "Auth Code Send Required"),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "AUTH-0005", "Already Exist User"),
    NOT_VALID_MEMBER(HttpStatus.BAD_REQUEST, "AUTH-0006", "Not Valid Member"),
    WAITING_MEMBER_ERROR(HttpStatus.BAD_REQUEST, "AUTH-0007", "Waiting Member"),
    REJECTED_MEMBER_ERROR(HttpStatus.BAD_REQUEST, "AUTH-0008", "Rejected Member"),
    UNAUTHORIZED_PHONE_NUMBER(HttpStatus.UNAUTHORIZED, "AUTH-0009", "Unauthorized Phone Number"),
    NOT_EXIST_PART_OR_UNIT(HttpStatus.BAD_REQUEST, "AUTH-0010", "Not Exist Part Or Unit"),
    INVALID_ID_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH-0011", "Invalid Id or Password"),

    // Token
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN-0001", "Invalid token");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
