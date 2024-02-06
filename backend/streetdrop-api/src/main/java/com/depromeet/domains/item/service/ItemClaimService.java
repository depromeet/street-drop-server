package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.error.ItemErrorCode;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.item.ItemClaim;
import com.depromeet.report.claim.dto.ItemClaimReportDto;
import com.depromeet.report.claim.service.ItemClaimReportService;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.item.vo.ItemClaimStatus.WAITING;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemRepository itemRepository;
    private final ItemClaimReportService itemClaimReportService;

    @Transactional
    public void claimItem(User user, ItemClaimRequestDto itemClaimRequestDto) {
        var itemId = itemClaimRequestDto.getItemId();
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(CommonErrorCode.NOT_FOUND, String.valueOf(itemId)));
        checkUserAlreadyReport(user.getId(), itemId);

        var itemClaim = ItemClaim.builder()
                .reason(itemClaimRequestDto.getReason())
                .itemId(itemId)
                .userId(user.getId())
                .itemContent(item.getContent())
                .status(WAITING)
                .build();

        var saveditemClaim = itemClaimRepository.save(itemClaim);

        ItemClaimReportDto itemClaimReportDto = ItemClaimReportDto.builder()
                .itemClaimId(saveditemClaim.getId())
                .itemClaimReason(saveditemClaim.getReason())
                .itemClaimStatus(saveditemClaim.getStatus())
                .reportUserId(saveditemClaim.getUserId())
                .itemId(saveditemClaim.getItemId())
                .itemContent(item.getContent())
                .build();

        itemClaimReportService.sendReport(itemClaimReportDto);
    }

    private void checkUserAlreadyReport(Long userId, Long itemId) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(userId, itemId);
        if (alreadyReported) {
            throw new BusinessException(ItemErrorCode.ITEM_ALREADY_REPORTED);
        }
    }
}
