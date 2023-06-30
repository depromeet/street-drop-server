package com.depromeet.domains.user.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.user.User;
import com.depromeet.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
	public static final int NICKNAME_MIN_LENGTH = 1;
	public static final int NICKNAME_MAX_LENGTH = 10;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public User getOrCreateUser(String nickname) {
		return userRepository.findUserByNickname(nickname)
				.orElseGet(() -> User.builder()
						.nickname(nickname)
						.build()
		);
	}

    @Transactional
    public User getOrCreateUserByIdfv(String idfv) {
        Optional<User> user = userRepository.findUserByIdfv(idfv);
        if (user.isPresent()) {
            return user.get();
        } else {
            User newUser = User.builder()
                    .nickname(generateDefaultNickname())
                    .idfv(idfv)
                    .build();
            return userRepository.save(newUser);
        }
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return new UserResponseDto(user);
    }

    private String generateDefaultNickname() {
        List<String> preName = Arrays.asList(
                "막강한", "강력한", "심심한", "강한",  "연한", "똑똑한", "부드러운", "잠을 자는",
                "착한", "친절한", "잘생긴", "귀여운",  "공부하는", "놀고있는", "게임하는", "책읽는", "아무생각이 없는",
                "부티나는", "아무것도 안하는", "총쏘는", "코딩하는", "밥먹는", "잠자는", "물 마시는", "게으른", "커피 마시는",
                "효율적인", "웃는","노란", "노르스름한", "연노랑", "핑크", "분홍", "연분홍", "자주색", "파란색", "검정색",
                "까만", "하얀", "가벼운"
        );

        List<String> postName = Arrays.asList(
                "부엉이", "올빼미", "사람", "인간", "댕댕이", "멍멍이", "고양이", "거미", "개미", "달팽이", "사자", "호랑이",
                "오징어", "문어", "꼴뚜기", "갑오징어", "세발낙지", "낙지", "마라탕", "치킨", "선생님", "학생", "햄버거",
                "버거", "불고기버거", "몬스터 와퍼", "핫도그", "알파고", "레이너", "디바", "솔져", "트레이서", "키보드", "책",
                "스크래치", "엔트리", "루비", "리눅스", "맥", "우분투", "칼리", "애쉬", "매버릭", "라인하르트", "리퍼", "아이폰",
                "애플워치", "이재용"
        );

        return preName.get((int) (Math.random() * preName.size()))
                + " " + postName.get((int) (Math.random() * postName.size()));
    }

	@Transactional
	public UserResponseDto changeNickname(User user, String nickname) {
		validateNicknameLength(nickname);

		var findUser = userRepository.findUserByIdfv(user.getIdfv())
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, user.getId()))
				.changeNickname(nickname);

		return new UserResponseDto(findUser);
	}

	private void validateNicknameLength(String nickname) {
		if (nickname.length() < NICKNAME_MIN_LENGTH || nickname.length() > NICKNAME_MAX_LENGTH) {
			throw new BusinessException(ErrorCode.INVALID_INPUT_EXCEPTION);
		}
	}
}
