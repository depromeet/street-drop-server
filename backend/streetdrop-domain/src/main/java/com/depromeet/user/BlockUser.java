package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class BlockUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_id")
	private Long id;

	private Long blockerId;

	private Long blockedId;

	@Builder
	public BlockUser(Long blockerId, Long blockedId) {
		this.blockerId = blockerId;
		this.blockedId = blockedId;
	}
}
