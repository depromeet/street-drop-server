package com.depromeet.streetdrop.domains.item.dto.request;

import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationRequestDto;
import com.depromeet.streetdrop.domains.music.dto.request.MusicRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@Validated
@AllArgsConstructor
public class ItemRequestDto {

	@Schema(description = "위치 정보")
	private LocationRequestDto location;

	@Schema(description = "음악 정보")
	private MusicRequestDto music;

	@Schema(description = "콘텐츠", example = "블라블라")
	private String content;
}
