package com.depromeet.auth.dto.resonse;

import com.depromeet.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MemberLoginLogAllResponseDto {
    private List<MemberLoginLogResponseDto> logs;
    private PageMetaData meta;
}
