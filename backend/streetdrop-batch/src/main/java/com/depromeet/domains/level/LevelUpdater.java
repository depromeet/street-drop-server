package com.depromeet.domains.level;

import com.depromeet.level.data.LevelPolicy;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class LevelUpdater {
	public static LevelPolicy getUpdateLevel(Long dropCount) {
		return Arrays.stream(LevelPolicy.values())
				.filter(policy -> dropCount >= policy.getDropCountStart() && dropCount <= policy.getDropCountEnd())
				.findFirst()
				.orElse(LevelPolicy.LEVEL_1);
	}
}
