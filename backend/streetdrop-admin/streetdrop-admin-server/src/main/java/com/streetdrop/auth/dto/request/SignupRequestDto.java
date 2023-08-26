package com.streetdrop.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SignupRequestDto {

    String username;

    String email;

    String part;

    String name;

    String password;

}
