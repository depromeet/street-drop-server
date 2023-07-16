package com.depromeet.user;

import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class BlockUser extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "block_id")
	private Long id;

	@Column(nullable = false)
	private Long blockerId;

	@Column(nullable = false)
	private Long blockedId;

	@Builder
	public BlockUser(Long blockerId, Long blockedId) {
		this.blockerId = blockerId;
		this.blockedId = blockedId;
	}
}
