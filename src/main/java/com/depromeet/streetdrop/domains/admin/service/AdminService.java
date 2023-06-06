package com.depromeet.streetdrop.domains.admin.service;

import com.depromeet.streetdrop.domains.user.dto.response.UserAllResponseDto;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public Page<UserAllResponseDto> searchAllUsers(Pageable pageable) {
		Page<User> user = findAll(pageable);
		var userResponseList = user.getContent()
				.stream()
				.map(UserAllResponseDto.UserResponseDto::new)
				.collect(Collectors.toList());

		List<UserAllResponseDto> userAllResponseList = new ArrayList<>();
		userAllResponseList.add(new UserAllResponseDto(userResponseList));
		return new PageImpl<>(userAllResponseList, pageable, user.getTotalElements());
	}

	@Transactional(readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
