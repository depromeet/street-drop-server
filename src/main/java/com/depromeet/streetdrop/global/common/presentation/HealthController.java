package com.depromeet.streetdrop.global.common.presentation;

import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.exception.common.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
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
