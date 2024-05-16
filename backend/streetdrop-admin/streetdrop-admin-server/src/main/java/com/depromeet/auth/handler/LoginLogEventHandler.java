package com.depromeet.auth.handler;

import com.depromeet.auth.event.LoginLogEvent;
import com.depromeet.auth.repository.MemberLoginLogRepository;
import com.depromeet.entity.MemberLoginLog;
import com.depromeet.global.security.provider.SecurityUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LoginLogEventHandler {

    private final MemberLoginLogRepository memberLoginLogRepository;

    @EventListener
    public void createLoginLog(LoginLogEvent loginLogEvent) {
        var request = loginLogEvent.getHttpServletRequest();
        var authentication = loginLogEvent.getAuthentication();
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();

        var memberLoginLog = MemberLoginLog.builder()

                .loginIp(getRemoteAddr(request))
                .userAgent(getUserAgent(request))
                .createdAt(LocalDateTime.now())
                .loginResult("Success")
                .build();

        memberLoginLogRepository.insert(memberLoginLog);
    }

    private String getRemoteAddr(HttpServletRequest request) {
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

    private String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }
}
