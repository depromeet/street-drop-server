package com.depromeet.domains.item.dto.request;

import com.depromeet.domains.music.dto.request.MusicRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
	@Valid
	private ItemLocationRequestDto location;

	@Schema(description = "음악 정보")
	@Valid
	private MusicRequestDto music;

	@Schema(description = "콘텐츠", example = "블라블라")
	@NotNull(message = "Content is required")
	private String content;
}
