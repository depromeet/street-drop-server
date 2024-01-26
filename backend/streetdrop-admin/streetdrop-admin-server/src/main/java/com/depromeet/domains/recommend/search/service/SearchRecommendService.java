package com.depromeet.domains.recommend.search.service;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.domains.recommend.search.dto.CreateSearchRecommendDto;
import com.depromeet.domains.recommend.search.dto.SearchRecommendResponseDto;
import com.depromeet.domains.recommend.search.repository.SearchRecommendTermRepository;
import com.depromeet.recommend.search.SearchRecommendTerm;
import com.depromeet.recommend.search.TextColorVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {
    private final SearchRecommendTermRepository searchRecommendTermRepository;

    @Transactional
    public int createSearchRecommendTerm(CreateSearchRecommendDto createSearchRecommendDto) {
        var title = createSearchRecommendDto.getTitle();
        var description = createSearchRecommendDto.getDescription().stream()
                .map(textColorDto -> new TextColorVo(textColorDto.getText(), textColorDto.getColor()))
                .toList();
        var terms = createSearchRecommendDto.getTerms().stream()
                .map(textColorDto -> new TextColorVo(textColorDto.getText(), textColorDto.getColor()))
                .toList();
        var searchRecommendTerm = SearchRecommendTerm.builder().title(title).description(description).terms(terms).build();
        var result = searchRecommendTermRepository.save(searchRecommendTerm);
        return result.getId().intValue();
    }

    @Transactional(readOnly = true)
    public PageResponseDto<SearchRecommendResponseDto> getRecommendSearchTerm(Pageable pageable) {
        var searchRecommendTerms = searchRecommendTermRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                searchRecommendTerms.getNumber(),
                searchRecommendTerms.getSize(),
                (int) searchRecommendTerms.getTotalElements(),
                searchRecommendTerms.getTotalPages()
        );

        List<SearchRecommendResponseDto> searchRecommendResponseDtos = searchRecommendTerms
                .stream()
                .map(
                        s -> new SearchRecommendResponseDto(
                                s.getId(),
                                s.getTitle(),
                                s.getDescription().stream().map(
                                        textColorVo -> new com.depromeet.domains.recommend.search.dto.TextColorDto(textColorVo.getText(), textColorVo.getColor())
                                ).toList(),
                                s.getTerms().stream().map(
                                        textColorVo -> new com.depromeet.domains.recommend.search.dto.TextColorDto(textColorVo.getText(), textColorVo.getColor())
                                ).toList(),
                                s.isActive()
                        )
                )
                .toList();

        return new PageResponseDto<>(searchRecommendResponseDtos, pageMetaData);
    }
}
