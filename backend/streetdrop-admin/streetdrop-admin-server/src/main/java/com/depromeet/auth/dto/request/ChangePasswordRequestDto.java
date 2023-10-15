package com.depromeet.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangePasswordRequestDto {
    private String prevPassword;
    private String newPassword;
}
