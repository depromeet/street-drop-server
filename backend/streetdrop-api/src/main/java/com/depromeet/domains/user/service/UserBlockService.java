package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.BlockUser;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserBlockService {

	private final UserRepository userRepository;
	private final BlockUserRepository blockUserRepository;

	@Transactional
	public BlockUserResponseDto blockUser(User user, Long blockUserID) {
		var blockUser  = userRepository.findUserById(blockUserID)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, user.getId()));

		/*
		 * blockerId : 차단한 사용자 아이디
		 * blockedId : 차단된 사용자 아이디
		 */
		BlockUser block = BlockUser.builder()
				.blockerId(user.getId())
				.blockedId(blockUserID)
				.build();
		blockUserRepository.save(block);
		return new BlockUserResponseDto(blockUser);
	}
}
