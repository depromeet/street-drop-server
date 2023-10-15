package com.depromeet.auth.handler;

import com.depromeet.auth.config.AdministratorConfig;
import com.depromeet.auth.dto.request.SignupRequestDto;
import com.depromeet.auth.event.CreateAdministratorEvent;
import com.depromeet.auth.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministratorEventHandler {

    private final MemberService memberService;
    private final AdministratorConfig administratorConfig;

    @EventListener
    public void createNewAdministrator(CreateAdministratorEvent createAdministratorEvent) {
        var signUpRequestDto = SignupRequestDto.builder()
                .username(administratorConfig.getId())
                .email("")
                .part("Admin")
                .name("관리자")
                .password(administratorConfig.getPassword())
                .build();

        memberService.signUp(signUpRequestDto);
    }


}
