package com.depromeet.domains.popup.service;

import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.popup.data.GuidePopupData;
import com.depromeet.domains.popup.data.LevelUpPopupData;
import com.depromeet.domains.popup.dto.request.PopupReadRequestDto;
import com.depromeet.domains.popup.dto.response.popupcontent.GuidePopupContent;
import com.depromeet.domains.popup.dto.response.popupcontent.LevelUpPopupContent;
import com.depromeet.domains.popup.dto.response.PopupResponseDto;
import com.depromeet.domains.popup.dto.response.PopupDataDto;
import com.depromeet.domains.popup.error.PopupErrorCode;
import com.depromeet.domains.popup.repository.PopupRepository;
import com.depromeet.popup.Popup;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PopupService {

    private final PopupRepository popupRepository;

    public PopupResponseDto getUserPopup(User user) {
        List<Popup> popUpList = popupRepository.findAllByUserId(user.getId());
        var resultList = new ArrayList<>();
        for (Popup popup : popUpList) {
            var popupDataDto = getPopUpData(popup.getId(), popup.getPopupName());
            if (popupDataDto.isPresent()) {
                resultList.add(popupDataDto);
            }
        }
        return new PopupResponseDto(resultList);
    }

    public void readUserPopup(User user, PopupReadRequestDto popupReadRequestDto) {
        var popUp = popupRepository.findById(popupReadRequestDto.getId())
                .orElseThrow(() -> new NotFoundException(PopupErrorCode.POPUP_NOT_FOUND));
        if (popUp.getUserId().equals(user.getId())) {
            popUp.setIsRead(true);
            popupRepository.save(popUp);
        }
    }

    private Optional<PopupDataDto<?>> getPopUpData(long id, String popUpName) {
        try {
            if (popUpName.startsWith("LEVEL")) {
                LevelUpPopupData popupContentData = LevelUpPopupData.valueOf(popUpName);
                return Optional.of(new PopupDataDto<>(new LevelUpPopupContent(id, popupContentData)));
            } else if (popUpName.startsWith("GUIDE")) {
                GuidePopupData popupContentData = GuidePopupData.valueOf(popUpName);
                return Optional.of(new PopupDataDto<>(new GuidePopupContent(id, popupContentData)));
            } else {
                return Optional.empty();
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
