package com.streetdrop.domains.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@Validated
@AllArgsConstructor
public class ItemUpdateRequestDto {

	@Schema(description = "콘텐츠", example = "블라블라")
	@NotNull(message = "Content is required")
	private String content;
}
