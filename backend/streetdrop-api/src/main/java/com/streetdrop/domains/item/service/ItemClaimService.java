package com.streetdrop.domains.item.service;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.common.error.exception.common.NotFoundException;
import com.streetdrop.domains.item.dto.request.ItemClaimRequestDto;
import com.streetdrop.domains.item.repository.ItemClaimRepository;
import com.streetdrop.domains.item.repository.ItemRepository;
import com.streetdrop.item.ItemClaim;
import com.streetdrop.report.dto.ItemClaimReportDto;
import com.streetdrop.report.service.SlackItemClaimReportService;
import com.streetdrop.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.streetdrop.item.vo.ItemClaimStatus.WAITING;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemRepository itemRepository;
    private final SlackItemClaimReportService slackItemClaimReportService;

    @Transactional
    public void claimItem(User user, ItemClaimRequestDto itemClaimRequestDto) {
        var itemId = itemClaimRequestDto.getItemId();
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, String.valueOf(itemId)));
        checkUserAlreadyReport(user.getId(), itemId);

        var itemClaim = ItemClaim.builder()
                .reason(itemClaimRequestDto.getReason())
                .itemId(itemId)
                .userId(user.getId())
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

        slackItemClaimReportService.sendReport(itemClaimReportDto);
    }

    private void checkUserAlreadyReport(Long userId, Long itemId) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(userId, itemId);
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}
