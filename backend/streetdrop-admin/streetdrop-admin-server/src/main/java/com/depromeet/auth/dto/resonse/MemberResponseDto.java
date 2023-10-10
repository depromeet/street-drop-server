package com.depromeet.auth.dto.resonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private Long id;

    private String email;

    private String userId;

    private String name;

    private String part;

}
