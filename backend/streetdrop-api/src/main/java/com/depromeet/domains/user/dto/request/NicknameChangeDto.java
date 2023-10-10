package com.depromeet.domains.user.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Validated
@ParameterObject
public class NicknameChangeDto {

    @NotNull(message = "Nickname is required")
    @Size(min = 1, max = 10, message = "닉네임은 한글자 이상 10글자 이하입니다.")
    private String nickname;

}
