package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users_level")
public class UserLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "level_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String image;

	@OneToMany(mappedBy = "userLevel")
	private List<User> users = new ArrayList<>();

	@Builder
	public UserLevel(String name, String description, String image, List<User> users) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.users = users;
	}
}
