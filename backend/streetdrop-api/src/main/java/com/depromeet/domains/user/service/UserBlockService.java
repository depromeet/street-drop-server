package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.user.dto.request.BlockUserRequestDto;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.item.Item;
import com.depromeet.user.Block;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBlockService {

	private final UserRepository userRepository;
	private final BlockUserRepository blockUserRepository;

	@Transactional
	public BlockUserResponseDto blockUser(User user, Long blockUserID) {
		var blockUser  = userRepository.findUserById(blockUserID)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, user.getId()));

		Block block = Block.builder()
				.blockerId(user.getId())    // 차단한 사용자 아이디
				.blockedId(blockUserID)     // 차단된 사용자 아이디
				.build();

		blockUserRepository.save(block);
		return new BlockUserResponseDto(blockUser);
	}

	@Transactional
	public List<Item> getItemsByUserId(Long userId) {
		return userRepository.findUserById(userId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, userId))
				.getItems();
	}
}
