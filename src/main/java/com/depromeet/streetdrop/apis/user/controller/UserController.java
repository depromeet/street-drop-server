package com.depromeet.streetdrop.apis.user.controller;

import com.depromeet.streetdrop.domains.aws.AwsS3Service;
import com.depromeet.streetdrop.domains.aws.dto.vo.S3ImageCategory;
import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.user.dto.response.UserResponseDto;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.domains.user.service.UserService;
import com.depromeet.streetdrop.global.annotation.ReqUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User API")
public class UserController {
    private final UserService userService;
    private final AwsS3Service awsS3Service;

    @Operation(summary = "내 정보 가져오기")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserInfo(
            @ReqUser User user
    ) {
        var response = userService.getUserInfo(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자 프로필 이미지 수정")
    @PatchMapping(value = "/me/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> updateProfileImage(
            @ReqUser User user,
            @RequestPart(value = "file") List<MultipartFile> multipartFiles
    ) {
        List<String> profileImageUrls = awsS3Service.uploadFilesToS3(multipartFiles, S3ImageCategory.USER_PROFILE);
        var response = userService.updateProfileImage(user, profileImageUrls.get(0));
        return ResponseDto.ok(response);
    }

}
