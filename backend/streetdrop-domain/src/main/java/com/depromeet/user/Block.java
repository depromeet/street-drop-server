package com.depromeet.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Block {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "block_id")
	private Long id;

	private Long blockerId;

	private Long blockedId;

	@Builder
	public Block(Long blockerId, Long blockedId) {
		this.blockerId = blockerId;
		this.blockedId = blockedId;
	}
}
