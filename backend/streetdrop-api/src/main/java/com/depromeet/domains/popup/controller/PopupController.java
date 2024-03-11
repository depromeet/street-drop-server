package com.depromeet.domains.popup.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.popup.dto.request.PopupReadRequestDto;
import com.depromeet.domains.popup.dto.response.PopupResponseDto;
import com.depromeet.domains.popup.service.PopupService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pop-up")
@RequiredArgsConstructor
@Tag(name = "Pop Up", description = "Pop Up API")
public class PopupController {
    private final PopupService popupService;


    @Operation(summary = "Get User Popup")
    @GetMapping
    public ResponseEntity<PopupResponseDto> getUserPopup(
            @ReqUser User user
    ) {
        var response = popupService.getUserPopup(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "user Popup to Read")
    @PostMapping("/read")
    public ResponseEntity<Void> readUserPopup(
            @ReqUser User user,
            @RequestBody PopupReadRequestDto popupReadRequestDto
            ){
        popupService.readUserPopup(user, popupReadRequestDto);
        return ResponseDto.noContent();
    }
}
