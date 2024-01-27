package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.user.User;
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
				.orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, user.getUserLevelId()));
		return new UserLevelResponseDto(user, level);
	}
}

