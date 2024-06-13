package com.depromeet.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PageMetaResponseDto implements MetaInterface {
	private int page;
	private int size;
	private int totalPage;
	private boolean firstPage;
	private boolean lastPage;
}