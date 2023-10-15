package com.depromeet.auth.event;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginLogEvent {
    private HttpServletRequest httpServletRequest;
    private Authentication authentication;
}
