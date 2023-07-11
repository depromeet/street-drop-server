package com.depromeet.apis.user.dto;

import com.depromeet.apis.common.dto.PageMetaData;
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
