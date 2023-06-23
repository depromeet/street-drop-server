package com.depromeet.apis.user.controller;


import com.depromeet.apis.user.dto.UserAllResponseDto;
import com.depromeet.apis.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping("/users")
	public Page<UserAllResponseDto> getUsers(
			@PageableDefault(size = 20, page = 0, sort = "createdAt",
					direction = Sort.Direction.DESC) Pageable pageable
			) {
		return userService.searchAllUsers(pageable);
	}
}
