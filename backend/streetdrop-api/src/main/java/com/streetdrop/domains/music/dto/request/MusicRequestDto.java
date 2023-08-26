package com.streetdrop.domains.music.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MusicRequestDto {
	@Schema(description = "노래제목", example = "하입보이")
	@NotNull(message = "Title is required")
	private String title;

	@Schema(description = "아티스트명", example = "New jeans")
	@NotNull(message = "Artist is required")
	private String artist;

	@Schema(description = "앨범명", example = "1st EP New jeans")
	@NotNull(message = "Album name is required")
	private String albumName;

	@Schema(description = "앨범 커버 이미지", example = "https://www.youtube.com/watch?v=YGieI3KoeZk")
	@NotNull(message = "Album image is required")
	private String albumImage;

	@Schema(description = "장르")
	@NotNull(message = "Genre is required")
	private List<String> genre;
}