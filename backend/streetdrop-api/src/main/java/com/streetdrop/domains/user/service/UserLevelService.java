package com.streetdrop.domains.user.service;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.NotFoundException;
import com.streetdrop.domains.user.dto.response.UserLevelResponseDto;
import com.streetdrop.domains.user.repository.UserLevelRepository;
import com.streetdrop.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLevelService {

	private final UserLevelRepository userLevelRepository;

	@Transactional(readOnly = true)
	public UserLevelResponseDto getUserLevel(User user) {
		var level = userLevelRepository.findById(user.getUserLevelId())
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, user.getUserLevelId()));
		return new UserLevelResponseDto(user, level);
	}
}

