package com.depromeet.auth.handler;

import com.depromeet.auth.config.AdministratorConfig;
import com.depromeet.auth.dto.request.SignupRequestDto;
import com.depromeet.auth.event.CreateAdministratorEvent;
import com.depromeet.auth.service.MemberService;
import com.depromeet.entity.Part;
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
                .part(Part.Admin)
                .name("관리자")
                .password(administratorConfig.getPassword())
                .build();
        if (!memberService.existsByUsername(signUpRequestDto.getUsername())) {
            memberService.signUp(signUpRequestDto);
        }
    }


}
