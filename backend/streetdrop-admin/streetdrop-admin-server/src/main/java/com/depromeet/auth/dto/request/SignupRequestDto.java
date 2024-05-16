package com.depromeet.auth.dto.request;

import com.depromeet.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class SignupRequestDto {

    String username;

    String email;

    Part part;

    String name;

    String password;

}
