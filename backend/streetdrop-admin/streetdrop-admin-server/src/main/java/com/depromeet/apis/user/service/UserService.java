package com.depromeet.apis.user.service;

import com.depromeet.apis.user.dto.UserAllResponseDto;
import com.depromeet.apis.user.repository.UserRepository;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserAllResponseDto searchAllUsers(Pageable pageable) {
		Page<User> user = userRepository.findAll(pageable);
		var userResponseDtoList = user.getContent()
				.stream()
				.map(UserAllResponseDto.UserResponseDto::new)
				.toList();
		var userAllResponseDtoList = new ArrayList<>();
		userAllResponseDtoList.add(new UserAllResponseDto(userResponseDtoList));
		return new UserAllResponseDto(userResponseDtoList);
	}
}
