package com.depromeet.auth.service;

import com.depromeet.auth.dto.request.SignupRequestDto;
import com.depromeet.auth.dto.resonse.JwtTokenResponseDto;
import com.depromeet.auth.dto.resonse.MemberAllResponseDto;
import com.depromeet.auth.dto.resonse.MemberInfoResponseDto;
import com.depromeet.auth.dto.resonse.MemberResponseDto;
import com.depromeet.auth.entity.Member;
import com.depromeet.auth.repository.MemoryMemberRepository;
import com.depromeet.common.dto.PageMetaData;
import com.depromeet.exception.BusinessException;
import com.depromeet.exception.ErrorCode;
import com.depromeet.global.security.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public MemberAllResponseDto getAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        var memberResponseDtoList = memberList.stream().map(
                member -> MemberResponseDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .userId(member.getUsername())
                        .name(member.getName())
                        .part(member.getPart())
                        .build()
        ).toList();

        var pageMetaData = new PageMetaData(
                1,
                30,
                memberResponseDtoList.size(),
                1
        );

        return new MemberAllResponseDto(memberResponseDtoList, pageMetaData);
    }


    public void changePassword(Member member, String prevPassword, String newPassword){
        if (passwordEncoder.matches(prevPassword, member.getPassword())) {
            member.setPassword(passwordEncoder.encode(newPassword));
        }
        else {
            throw new BusinessException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }
}
