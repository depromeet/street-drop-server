package com.depromeet.auth.dto.resonse;

import com.depromeet.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberAllResponseDto {
    List<MemberResponseDto> members;
    PageMetaData meta;
}
