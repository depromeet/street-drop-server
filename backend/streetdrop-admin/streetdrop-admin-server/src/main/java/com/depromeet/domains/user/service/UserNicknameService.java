package com.depromeet.domains.user.service;


import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.domains.user.dto.UserNicknameCreateRequestDto;
import com.depromeet.domains.user.repository.UserNicknameRepository;
import com.depromeet.user.DefaultNickName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserNicknameService {

    private final UserNicknameRepository userNicknameRepository;

    public PageResponseDto<DefaultNickName> getAllUserNicknames(Pageable pageable) {
        var userNickname =  userNicknameRepository.findAll(pageable);

        PageMetaData pageMetaData = new PageMetaData(
                userNickname.getNumber(),
                userNickname.getSize(),
                (int) userNickname.getTotalElements(),
                userNickname.getTotalPages()
        );


        List<DefaultNickName> userNicknameList = userNickname.getContent()
                .stream()
                .toList();


        return new PageResponseDto<>(userNicknameList, pageMetaData);

    }

    public DefaultNickName createUserNickname(UserNicknameCreateRequestDto userNicknameCreateRequestDto) {
        var userNickname = new DefaultNickName(
                userNicknameCreateRequestDto.getPreNickname(),
                userNicknameCreateRequestDto.getPostNickname()
        );
        return userNicknameRepository.save(userNickname);
    }
}
