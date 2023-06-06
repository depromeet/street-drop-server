package com.depromeet.streetdrop.apis.admin.controller;

import com.depromeet.streetdrop.domains.admin.service.AdminService;
import com.depromeet.streetdrop.domains.user.dto.response.UserAllResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API")
public class AdminController {
	private final AdminService adminService;

	@Operation(summary = "전체 사용자 정보 조회")
	@GetMapping("/users")
	public Page<UserAllResponseDto> getUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "createdAt,desc") String[] sort
			) {
		var pageable = PageRequest.of(page, size, Sort.by(sort[0]).descending());
		return adminService.searchAllUsers(pageable);
	}
}