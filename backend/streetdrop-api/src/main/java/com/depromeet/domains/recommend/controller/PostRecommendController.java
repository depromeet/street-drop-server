package com.depromeet.domains.recommend.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.recommend.dto.response.PostRecommendSentenceResponseDto;
import com.depromeet.domains.recommend.service.PostRecommendService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "💁‍♀️Post Recommend", description = "Post Recommend API")
public class PostRecommendController {

    private final PostRecommendService postRecommendService;

    @Operation(summary = "홈 화면 드랍 유도 - 무작위 문장 추천")
    @GetMapping("/post-recommend/random-sentence")
    public ResponseEntity<PostRecommendSentenceResponseDto> getRandomPhrase(
            @ReqUser User user
    ) {
        var response = postRecommendService.getOneRandomSentence(user);
        return ResponseDto.ok(response);
    }

}
