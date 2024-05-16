package com.depromeet.auth.dto.resonse;

import com.depromeet.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponseDto {
    private String name;

    private Part part;
}
