package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.user.dto.response.UserLevelProgressDto;
import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.level.data.LevelPolicy;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.depromeet.level.data.LevelPolicy.getNextLevel;


@RequiredArgsConstructor
@Service
public class UserLevelService {

	private final UserLevelRepository userLevelRepository;
	private final ItemRepository itemRepository;

	@Transactional(readOnly = true)
	public UserLevelResponseDto getUserLevel(User user) {
		var level = userLevelRepository.findById(user.getUserLevelId())
				.orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, user.getUserLevelId()));
		return new UserLevelResponseDto(user, level);
	}

	@Transactional(readOnly = true)
	public UserLevelProgressDto getUserLevelProgress(User user) {
		Long dropCount = itemRepository.countByUser(user);
		var currentLevel = Arrays.stream(LevelPolicy.values())
				.filter(policy -> dropCount >= policy.getDropCountStart() && dropCount < policy.getDropCountEnd())
				.findFirst()
				.orElse(LevelPolicy.LEVEL_1);
		LevelPolicy nextLevel = getNextLevel(currentLevel);
		var remainCount = nextLevel.getDropCountStart() - dropCount;
		var isShow = !(currentLevel == LevelPolicy.LEVEL_3);
		var tipMessage = "레벨업하면 음악을 들을 수 있는 반경이 넓어져요!";
		return new UserLevelProgressDto(isShow, remainCount, dropCount, nextLevel.getDropCountStart(), tipMessage);
	}

}

