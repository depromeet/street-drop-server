package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.service.UserBlockService;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.external.swagger.annotation.ApiErrorResponses;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "🙅User Block", description = "User Block API")
public class UserBlockController {
	private final UserBlockService userBlockService;

	@Operation(summary = "사용자 차단")
	@ApiResponse(responseCode = "200", description = "차단 성공")
	@ApiErrorResponses(value = {
			@ApiErrorResponse(errorCode = "USER_NOT_FOUND", description = "차단하려는 사용자가 존재하지 않음"),
			@ApiErrorResponse(errorCode = "USER_CAN_NOT_BLOCK_SELF", description = "자기 자신을 차단할 수 없습니다.")
	})
	@PostMapping("/users/block")
	public ResponseEntity<BlockUserResponseDto> blockUser(
			@ReqUser User user,
			@RequestParam(value = "blockUserID") Long blockUserID
	) {
		var response = userBlockService.blockUser(user, blockUserID);
		return ResponseDto.ok(response);
	}

	@Operation(summary = "사용자 차단 해제")
	@ApiResponse(responseCode = "204", description = "차단 해제 성공")
	@ApiErrorResponse(errorCode = "USER_NOT_FOUND", description = "차단 해제하려는 사용자가 존재하지 않음")
	@DeleteMapping("/users/unblock")
	public ResponseEntity<Void> unBlockUser(
			@ReqUser User user,
			@RequestParam(value = "unblockUserId") Long unblockUserId
	) {
		userBlockService.unBlockUser(user, unblockUserId);
		return ResponseDto.noContent();
	}
}
