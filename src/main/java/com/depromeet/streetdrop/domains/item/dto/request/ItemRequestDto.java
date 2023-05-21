package com.depromeet.streetdrop.domains.item.dto.request;

import com.depromeet.streetdrop.domains.itemLocation.dto.request.LocationDto;
import com.depromeet.streetdrop.domains.music.dto.request.MusicDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@Validated
@AllArgsConstructor
public class ItemRequestDto {

	private LocationDto location;

	private MusicDto music;

	@Schema(description = "콘텐츠", example = "블라블라")
	private String content;
}
