package com.depromeet.apis.user.controller;


import com.depromeet.apis.user.dto.ResponseDto;
import com.depromeet.apis.user.dto.UserAllResponseDto;
import com.depromeet.apis.user.dto.UserCountResponseDto;
import com.depromeet.apis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/users")
	public ResponseEntity<UserAllResponseDto> getUsers(
			@PageableDefault(size = 20, page = 0, sort = "createdAt",
					direction = Sort.Direction.DESC) Pageable pageable
			) {
		var response = userService.searchAllUsers(pageable);
		return ResponseDto.ok(response);
	}

	@GetMapping("/users/count")
	public ResponseEntity<List<UserCountResponseDto>> getUsersCount() {
		var response= userService.countUsersByCreatedAt();
		return ResponseEntity.ok(response);
	}
}
