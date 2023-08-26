package com.streetdrop.domains.user.service;

import com.streetdrop.common.dto.PageMetaData;
import com.streetdrop.domains.item.repository.ItemRepository;
import com.streetdrop.domains.user.dto.*;
import com.streetdrop.domains.user.repository.UserRepository;
import com.streetdrop.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public UserAllResponseDto getAllUsers(Pageable pageable) {
        Page<User> user = userRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                user.getNumber(),
                user.getSize(),
                (int) user.getTotalElements(),
                user.getTotalPages()
        );
        List<UserResponseDto> users = user.getContent()
                .stream()
                .map(
                        u -> new UserResponseDto(
                                u.getId(),
                                u.getNickname(),
                                u.getIdfv(),
                                u.getCreatedAt().toString()
                        )
                )
                .toList();
        return new UserAllResponseDto(users, pageMetaData);
    }


    @Transactional(readOnly = true)
    public UserDetailResponseDto getUserDetail(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        var userBasicInfoResponseDto = new UserBasicInfoResponseDto(user.getId(), user.getNickname(), user.getIdfv(), user.getCreatedAt());

        List<Object[]> result = userRepository.countUserDropItemByVillage(userId);

        String mainDropLocation = result.isEmpty() ? "현재 드랍한 아이템이 없습니다." : result.get(0)[0].toString();
        StringBuilder dropLocations = new StringBuilder(result.isEmpty() ? "현재 드랍한 아이템이 없습니다." : "");
        int allDropCount = 0;

        for (Object[] row : result) {
            dropLocations.append(row[0].toString()).append("[").append(row[1].toString()).append("]").append(", ");
            allDropCount += Integer.parseInt(row[1].toString());
        }
        var userDetailInfoResponseDto = new UserDetailInfoResponseDto(allDropCount, mainDropLocation, dropLocations.toString(), "지원 예정");

        var userActivityResponseDtoList = itemRepository.findByUser(user).stream().map(
                item -> {
                    String title = "'" + item.getItemLocation().getName() + "'" + " 에 아이템을 드랍했습니다.";
                    String content = "'" + item.getSong().getName() + "' 곡을 '" + item.getContent() + "' 의 내용을 드랍했습니다.";
                    return new UserActivityResponseDto(title, content);
                }
        ).toList();

        return new UserDetailResponseDto(userBasicInfoResponseDto, userDetailInfoResponseDto, userActivityResponseDtoList);
    }
}
