package com.depromeet.domains.user.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @PageableDefault(size = 20, page = 0, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = userService.getAllUsers(pageable);
        return ResponseDto.ok(response);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserDetail(
            @PathVariable("userId") Long userId
    ) {
        var response = userService.getUserDetail(userId);
        return ResponseDto.ok(response);
    }
}
