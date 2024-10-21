package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.recommend.dto.response.PostRecommendSentenceResponseDto;
import com.depromeet.domains.recommend.provider.RandomProvider;
import com.depromeet.domains.recommend.repository.PostRecommendSentenceRepository;
import com.depromeet.domains.recommend.repository.UserRecommendSendHistoryRepository;
import com.depromeet.recommend.post.PostRecommendSentence;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostRecommendService {

    private final PostRecommendSentenceRepository postRecommendSentenceRepository;
    private final UserRecommendSendHistoryRepository userRecommendSendHistoryRepository;

    public PostRecommendSentenceResponseDto getOneRandomSentence(User user) {
        if (userRecommendSendHistoryRepository.isSent(user.getId())) {
            return PostRecommendSentenceResponseDto.empty();
        }
        String randomSentence = getRandomSentence();
        userRecommendSendHistoryRepository.save(user.getId());
        return new PostRecommendSentenceResponseDto(randomSentence);
    }

    private String getRandomSentence() {
        var postRecommendSentences = postRecommendSentenceRepository.findAll();
        if (postRecommendSentences.isEmpty()) {
            throw new NotFoundException(CommonErrorCode.SENTENCES_NOT_FOUND);
        }
        PostRecommendSentence randomPostRecommendSentence = RandomProvider.getRandomElement(postRecommendSentences);
        return randomPostRecommendSentence.getSentence();
    }

}
