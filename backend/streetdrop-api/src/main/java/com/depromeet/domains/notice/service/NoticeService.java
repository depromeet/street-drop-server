package com.depromeet.domains.notice.service;

import com.depromeet.common.dto.MetaInterface;
import com.depromeet.common.dto.PageMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.notice.dto.response.NoticeListResponseDto;
import com.depromeet.domains.notice.dto.response.NoticeResponseDto;
import com.depromeet.domains.notice.dto.response.NewNoticeResponseDto;
import com.depromeet.domains.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public PaginationResponseDto<NoticeListResponseDto, MetaInterface> getNotices() {
        var notices = noticeRepository.findAll()
                .stream()
                .map(NoticeListResponseDto::new)
                .toList();
        var meta = PageMetaResponseDto.builder()
                .page(1)
                .size(notices.size())
                .totalPage(1)
                .firstPage(true)
                .lastPage(true)
                .build();
        return new PaginationResponseDto<>(notices, meta);
    }

    @Transactional(readOnly = true)
    public NoticeResponseDto getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .map(NoticeResponseDto::new)
                .orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, noticeId));
    }

    @Transactional(readOnly = true)
    public NewNoticeResponseDto hasNewNotice(Long lastNoticeId) {
        var isExist = noticeRepository.existsByIdGreaterThan(lastNoticeId);
        return new NewNoticeResponseDto(isExist);
    }

}
