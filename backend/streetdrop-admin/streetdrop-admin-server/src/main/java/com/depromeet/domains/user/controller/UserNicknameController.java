package com.depromeet.domains.user.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.UserNicknameCreateRequestDto;
import com.depromeet.domains.user.service.UserNicknameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/nickname")
@RequiredArgsConstructor
public class UserNicknameController {

    private final UserNicknameService userNicknameService;


    @GetMapping
    public ResponseEntity<?> getAllUserNicknames(
            @PageableDefault(size = 20, page = 0, sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = userNicknameService.getAllUserNicknames(pageable);
        return ResponseDto.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUserNickname(
            UserNicknameCreateRequestDto userNicknameCreateRequestDto
    ) {
        var response = userNicknameService.createUserNickname(userNicknameCreateRequestDto);
        return ResponseEntity.ok(response);
    }


}
