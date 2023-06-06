package com.depromeet.streetdrop.apis.admin.controller;

import com.depromeet.streetdrop.domains.admin.service.AdminService;
import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin API")
public class AdminController {
	private final AdminService adminService;

	@Operation(summary = "전체 사용자 정보 조회")
	@GetMapping("/users")
	public ResponseEntity<?> getUsers() {
		var userAllResponseDto = adminService.searchAll();
		return ResponseDto.ok(userAllResponseDto);
	}
}
