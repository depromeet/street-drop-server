package com.depromeet.domains.announcement.service;

import com.depromeet.announcement.Announcement;
import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.domains.announcement.dto.request.AnnouncementRequestDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;


    public PageResponseDto<AnnouncementResponseDto> getAnnouncements(Pageable pageable) {

        var announcement = announcementRepository.findAll(pageable);

        PageMetaData pageMetaData = new PageMetaData(
                announcement.getNumber(),
                announcement.getSize(),
                (int) announcement.getTotalElements(),
                announcement.getTotalPages()
        );

        List<AnnouncementResponseDto> announcements = announcement.getContent()
                .stream()
                .map((anc) -> {
                    return AnnouncementResponseDto.builder()
                            .id(anc.getId())
                            .title(anc.getTitle())
                            .content(anc.getContent())
                            .createdAt(anc.getCreatedAt())
                            .build();
                }).toList();

        return new PageResponseDto<>(announcements, pageMetaData);
    }


    public AnnouncementResponseDto getAnnouncement(Long announcementId) {

        var announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));

        return AnnouncementResponseDto.builder()
                .id(announcement.getId())
                .title(announcement.getTitle())
                .content(announcement.getContent())
                .createdAt(announcement.getCreatedAt())
                .build();
    }

    public AnnouncementResponseDto createAnnouncement(AnnouncementRequestDto requestDto) {

        Announcement announcement = new Announcement(requestDto.getTitle(), requestDto.getContent());

        var announcementResult = announcementRepository.save(announcement);
        return AnnouncementResponseDto.builder()
                .id(announcementResult.getId())
                .title(announcementResult.getTitle())
                .content(announcementResult.getContent())
                .createdAt(announcementResult.getCreatedAt())
                .build();
    }

    public void deleteAnnouncement(Long announcementId) {
        announcementRepository.deleteById(announcementId);
    }

}
