package com.depromeet.domains.announcement.service;

import com.depromeet.common.dto.MetaInterface;
import com.depromeet.common.dto.PageMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.announcement.dto.response.AnnouncementListResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional(readOnly = true)
    public PaginationResponseDto<AnnouncementListResponseDto, MetaInterface> getAnnouncements() {
        var announcements = announcementRepository.findAll()
                .stream()
                .map(AnnouncementListResponseDto::new)
                .toList();
        var meta = PageMetaResponseDto.builder()
                .page(1)
                .size(announcements.size())
                .totalPage(1)
                .firstPage(true)
                .lastPage(true)
                .build();
        return new PaginationResponseDto<>(announcements, meta);
    }

    @Transactional(readOnly = true)
    public AnnouncementResponseDto getAnnouncement(Long announcementId) {
        return announcementRepository.findById(announcementId)
                .map(AnnouncementResponseDto::new)
                .orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, announcementId));
    }

}
