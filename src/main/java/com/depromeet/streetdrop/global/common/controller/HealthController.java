package com.depromeet.streetdrop.global.common.controller;

import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.exception.common.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Application Health Good!";
    }

    @GetMapping("/test")
    public String test() {
        throw new NotFoundException(ErrorCode.NOT_FOUND);
    }
}
