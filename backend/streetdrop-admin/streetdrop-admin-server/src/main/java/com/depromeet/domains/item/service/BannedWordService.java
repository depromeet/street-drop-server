package com.depromeet.domains.item.service;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.domains.item.dto.BannedWordAllResponseDto;
import com.depromeet.domains.item.dto.BannedWordResponseDto;
import com.depromeet.domains.item.repository.BannedWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannedWordService {

    private final BannedWordRepository bannedWordRepository;

    public BannedWordAllResponseDto getBannedWords(Pageable pageable) {
        var bannedWord = bannedWordRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                bannedWord.getNumber(),
                bannedWord.getSize(),
                (int) bannedWord.getTotalElements(),
                bannedWord.getTotalPages()
        );

        List<BannedWordResponseDto> bannedWords = bannedWord.getContent()
                .stream()
                .map((word) -> {
                    return BannedWordResponseDto.builder()
                            .id(word.getId())
                            .word(word.getWord())
                            .build();
                }).toList();

        return new BannedWordAllResponseDto(bannedWords, pageMetaData);
    }
}
