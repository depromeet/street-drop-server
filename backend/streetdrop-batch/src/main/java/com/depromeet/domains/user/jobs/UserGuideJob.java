package com.depromeet.domains.user.jobs;

import com.depromeet.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGuideJob {
    private final UserService userService;

    public void run() {
        userService.createGuidePopup();
    }

}
