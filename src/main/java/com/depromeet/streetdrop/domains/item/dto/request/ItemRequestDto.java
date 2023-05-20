package com.depromeet.streetdrop.domains.item.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@NoArgsConstructor
@Validated
public class ItemRequestDto {
	private String latitude;
	private String longitude;
	private String address;
	private String title;
	private String artiest;
	private String albumName;
	private String albumImage;
	private List<String> genre;
	private String content;

	@Builder
	public ItemRequestDto(String latitude, String longitude, String address, String title,
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
