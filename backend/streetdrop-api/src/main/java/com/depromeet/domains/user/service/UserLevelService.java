package com.depromeet.domains.user.service;

import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.external.aws.s3.AwsS3Service;
import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import com.depromeet.user.vo.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserLevelService {

	private final AwsS3Service awss3Service;
	private final UserLevelRepository userLevelRepository;

	@Transactional
	public UserLevelResponseDto getOrCreateUserLevel(User user) {
		Optional<UserLevel> userLevel = userLevelRepository.findLevelByUserId(user.getId());

		if (userLevel.isPresent()) {
			return new UserLevelResponseDto(user, userLevel.get());
		} else {
			var s3BucketName = awss3Service.getS3FilePaths();
			var fileName = awss3Service.getFileName();
			var newUserLevel = UserLevel.builder()
					.level(Level.DROP_STARTER)
					.levelImage(awss3Service.getS3(s3BucketName, fileName))
					.build();

			userLevelRepository.save(newUserLevel);
			return new UserLevelResponseDto(user, newUserLevel);
		}
	}
}
