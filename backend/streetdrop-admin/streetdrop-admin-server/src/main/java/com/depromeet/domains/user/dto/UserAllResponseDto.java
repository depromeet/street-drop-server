package com.depromeet.domains.user.dto;

import com.depromeet.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAllResponseDto {
    List<UserResponseDto> users;
    PageMetaData meta;
}
