package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.service.UserBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/blocks")
@RequiredArgsConstructor
public class UserBlockController {

    private final UserBlockService userBlockService;

    @GetMapping
    public ResponseEntity<?> getUserBlockList(
            @PageableDefault(size = 20, page = 0, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = userBlockService.getUserBlockList(pageable);
        return ResponseDto.ok(response);
    }


}
