package com.depromeet.domains.announcement.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementsResponseDto;
import com.depromeet.domains.announcement.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional(readOnly = true)
    public AnnouncementsResponseDto getAnnouncements() {
        var announcements = announcementRepository.findAll()
                .stream()
                .map(AnnouncementResponseDto::new)
                .toList();
        return new AnnouncementsResponseDto(announcements);
    }

}
