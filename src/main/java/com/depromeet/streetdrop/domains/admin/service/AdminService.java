package com.depromeet.streetdrop.domains.admin.service;

import com.depromeet.streetdrop.domains.user.dto.response.UserAllResponseDto;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserAllResponseDto searchAll() {
		List<UserAllResponseDto.UserResponseDto> users = findAll().parallelStream()
				.map(UserAllResponseDto.UserResponseDto::new)
				.collect(Collectors.toList());

		return new UserAllResponseDto(users);
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
