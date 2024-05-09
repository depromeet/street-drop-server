package com.depromeet.domains.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserLevelProgressDto {
    @Schema(description = "노출 여부", example = "true")
    private Boolean isShow;
    @Schema(description = "남은 횟수를 나타냅니다.", example = "1")
    private Long remainCount;
    @Schema(description = "드롭된 횟수를 나타냅니다.", example = "4")
    private Long dropCount;
    @Schema(description = "레벨 업이 되는 횟수를 나타냅니다.", example = "5")
    private Long levelUpCount;
    @Schema(description = "팁 메시지를 나타냅니다.", example = "레벨업하면 음악을 들을 수 있는 반경이 넓어져요!")
    private String tip;
}
