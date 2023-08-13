package com.depromeet.auth.service;

import com.depromeet.auth.dto.request.SignupRequestDto;
import com.depromeet.auth.dto.resonse.JwtTokenResponseDto;
import com.depromeet.auth.dto.resonse.MemberInfoResponseDto;
import com.depromeet.auth.entity.Member;
import com.depromeet.auth.repository.MemoryMemberRepository;
import com.depromeet.global.security.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemoryMemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public JwtTokenResponseDto signUp(SignupRequestDto signupRequestDto) {
        Member member = Member.builder()
                .username(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .name(signupRequestDto.getName())
                .part(signupRequestDto.getPart())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build();

        Member newMember = memberRepository.save(member);
        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(newMember.getId()));
        String refreshToken = jwtTokenProvider.createRefreshToken(String.valueOf(newMember.getId()));
        return new JwtTokenResponseDto(accessToken, refreshToken);
    }


    public MemberInfoResponseDto getMyInfo(Member member) {
        return new MemberInfoResponseDto(member.getName(), member.getPart());
    }
}
