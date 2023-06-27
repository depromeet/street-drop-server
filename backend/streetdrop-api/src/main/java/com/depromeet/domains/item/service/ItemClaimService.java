package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.item.ItemClaim;
import com.depromeet.report.dto.ItemClaimReportDto;
import com.depromeet.report.service.SlackItemClaimReportService;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.item.ItemClaimStatus.WAITING;

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
                .item(item)
                .user(user)
                .status(WAITING)
                .build();

        var saveditemClaim = itemClaimRepository.save(itemClaim);

        slackItemClaimReportService.sendReport(new ItemClaimReportDto(saveditemClaim));
    }

    private void checkUserAlreadyReport(Long userId, Long itemId) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(userId, itemId);
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}
