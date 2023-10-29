package com.depromeet.report.block.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public record UserBlockReportDto(
		Long id,
		Long blockerId,
		Long blockedId,
		LocalDateTime blockTime
) {
	@Builder
	public UserBlockReportDto(Long id,
	                          Long blockerId,
	                          Long blockedId
	) {
		this(
				id,
				blockerId,
				blockedId,
				LocalDateTime.now()
		);
	}
}