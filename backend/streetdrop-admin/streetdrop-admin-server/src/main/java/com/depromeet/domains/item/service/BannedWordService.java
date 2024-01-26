package com.depromeet.domains.item.service;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.common.entity.BannedWord;
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

    public PageResponseDto<BannedWordResponseDto> getBannedWords(Pageable pageable) {
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

        return new PageResponseDto<>(bannedWords, pageMetaData);
    }

    public BannedWordResponseDto createBannedWord(String word) {
        BannedWord bannedWord = new BannedWord(word);
        var bannedWordResult = bannedWordRepository.save(bannedWord);
        return BannedWordResponseDto.builder()
                .id(bannedWordResult.getId())
                .word(bannedWordResult.getWord())
                .build();
    }

    public void deleteBannedWord(Long bannedWordId) {
        bannedWordRepository.deleteById(bannedWordId);
    }
}
