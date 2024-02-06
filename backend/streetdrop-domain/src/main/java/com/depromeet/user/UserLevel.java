package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_level")
public class UserLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_level_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String image;

	@Column(nullable = false)
	private Integer distance;

	@Builder
	public UserLevel(String name, String description, String image, Integer distance) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.distance = distance;
	}
}
