package com.depromeet.auth.dto.resonse;

import com.depromeet.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class MemberResponseDto {

    private String id;

    private String email;

    private String userId;

    private String name;

    private Part part;

}
