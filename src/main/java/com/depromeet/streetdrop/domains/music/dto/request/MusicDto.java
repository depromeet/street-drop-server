package com.depromeet.streetdrop.domains.music.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MusicDto {
	@Schema(description = "노래제목", example = "하입보이")
	private String title;

	@Schema(description = "아티스트명", example = "New jeans")
	private String artist;

	@Schema(description = "앨범명", example = "1st EP New jeans")
	private String albumName;

	@Schema(description = "앨범 커버 이미지")
	private String albumImage;

	@Schema(description = "장르")
	private List<String> genre;
}