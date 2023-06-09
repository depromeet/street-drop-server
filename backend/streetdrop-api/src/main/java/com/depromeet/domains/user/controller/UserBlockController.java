package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.service.UserBlockService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserBlockController {
	private final UserBlockService userBlockService;

	@Operation(summary = "사용자 차단하기")
	@PostMapping("/users/block")
	public ResponseEntity<BlockUserResponseDto> blockUser(
			@ReqUser User user,
			@RequestParam Long blockUserID
	) {
		var response = userBlockService.blockUser(user, blockUserID);
		return ResponseDto.ok(response);
	}
}
