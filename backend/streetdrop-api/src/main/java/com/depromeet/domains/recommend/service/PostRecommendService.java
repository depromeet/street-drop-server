package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.recommend.dto.response.PostRecommendSentenceResponseDto;
import com.depromeet.domains.recommend.repository.PostRecommendSentenceRepository;
import com.depromeet.recommend.post.PostRecommendSentence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PostRecommendService {

    private final PostRecommendSentenceRepository postRecommendSentenceRepository;
    private final Random random = new Random();

    public PostRecommendSentenceResponseDto getOneRandomSentence() {
        var all = postRecommendSentenceRepository.findAll();
        if (all.isEmpty()) {
            throw new NotFoundException(CommonErrorCode.SENTENCES_NOT_FOUND);
        }

        PostRecommendSentence randomPostRecommendSentence = all.get(random.nextInt(all.size()));
        String randomSentence = randomPostRecommendSentence.getSentence();
        return new PostRecommendSentenceResponseDto(randomSentence);
    }

}
