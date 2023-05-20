package com.depromeet.streetdrop.domains.item.dto.request;

import com.depromeet.streetdrop.domains.common.annotation.IsLatitude;
import com.depromeet.streetdrop.domains.common.annotation.IsLongitude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@NoArgsConstructor
@Validated
//@RequiredArgsConstructor
//@ParameterObject
public class ItemRequestDto {

	@Schema(description = "경도", example = "127.123456")
	@IsLongitude
	@NotNull(message = "Longitude is required")
	private Double latitude;

	@Schema(description = "위도", example = "37.123456")
	@IsLatitude
	@NotNull(message= "Latitude is required")
	private Double longitude;

	@Schema(description = "주소", example = "서울시 강남구 강남동")
	private String address;

	@Schema(description = "노래제목", example = "하입보이")
	private String title;

	@Schema(description = "아티스트명",  example = "New jeans")
	private String artiest;

	@Schema(description = "앨범명", example = "1st EP New jeans")
	private String albumName;

	@Schema(description = "앨범 커버 이미지")
	private String albumImage;

	@Schema(description = "장르")
	private List<String> genre;

	@Schema(description = "콘텐츠", example = "블라블라")
	private String content;

	@Builder
	public ItemRequestDto(Double latitude, Double longitude, String address, String title,
	                      String artiest, String albumName, String albumImage,
	                      List<String> genre, String content) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.title = title;
		this.artiest = artiest;
		this.albumName = albumName;
		this.albumImage = albumImage;
		this.genre = genre;
		this.content = content;
	}
}
