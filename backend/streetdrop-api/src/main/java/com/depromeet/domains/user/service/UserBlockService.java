package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.error.UserErrorCode;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.report.block.dto.UserBlockReportDto;
import com.depromeet.report.block.service.DiscordUserBlockReportService;
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
	private final DiscordUserBlockReportService discordUserBlockReportService;

	/*
	 * blockerId : 차단한 사용자 아이디
	 * blockedId : 차단된 사용자 아이디
	 */
	@Transactional
	public BlockUserResponseDto blockUser(User user, Long blockUserID) {
		var blockUser  = userRepository.findUserById(blockUserID)
				.orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, user.getId()));

		if (blockUser.getId().equals(user.getId())) {
			throw new BusinessException(UserErrorCode.USER_CAN_NOT_BLOCK_SELF);
		}

		BlockUser block = BlockUser.builder()
				.blockerId(user.getId())
				.blockedId(blockUserID)
				.build();

		var savedBlockUser = blockUserRepository.save(block);

		UserBlockReportDto userBlockReportDto = UserBlockReportDto.builder()
				.id(savedBlockUser.getId())
				.blockedId(savedBlockUser.getBlockedId())
				.blockerId(savedBlockUser.getBlockerId())
				.build();

		discordUserBlockReportService.sendReport(userBlockReportDto);

		return new BlockUserResponseDto(blockUser);
	}

	@Transactional
	public void unBlockUser(User user, Long unblockUserId) {
		var userId = user.getId();
		var blockedUser = blockUserRepository.findBlockUserByBlockerIdAndBlockedId(userId, unblockUserId)
				.orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, unblockUserId));
		blockUserRepository.delete(blockedUser);
	}
}
