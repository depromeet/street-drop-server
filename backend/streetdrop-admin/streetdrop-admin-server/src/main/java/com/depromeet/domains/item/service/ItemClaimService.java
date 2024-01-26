package com.depromeet.domains.item.service;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.domains.item.dto.ItemClaimResponseDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.item.ItemClaim;
import com.depromeet.item.vo.ItemClaimStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public PageResponseDto<ItemClaimResponseDto> getClaimItem(Pageable pageable) {
        var itemClaimPage = itemClaimRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                itemClaimPage.getNumber(),
                itemClaimPage.getSize(),
                (int) itemClaimPage.getTotalElements(),
                itemClaimPage.getTotalPages()
        );
        var itemPageData = itemClaimPage.getContent()
                .stream()
                .map((itemClaim) -> {
                    return ItemClaimResponseDto.builder()
                            .itemClaimId(itemClaim.getId())
                            .itemId(itemClaim.getItemId())
                            .itemContent(
                                    itemRepository.findById(itemClaim.getItemId())
                                            .isEmpty() ? "삭제된 Item" : itemRepository.findById(itemClaim.getItemId()).get().getContent()
                            )
                            .reason(itemClaim.getReason())
                            .status(itemClaim.getStatus())
                            .reportAt(itemClaim.getCreatedAt())
                            .build();
                }).toList();

        return new PageResponseDto<>(itemPageData, pageMetaData);
    }

    @Transactional
    public ItemClaim updateClaimItem(Long itemClaimId, ItemClaimStatus itemClaimStatus) {
        var itemClaim = itemClaimRepository.findById(itemClaimId);
        itemClaim.ifPresent((itemClaim1) -> {
            itemClaim1.setStatus(itemClaimStatus);
        });
        return itemClaim.get();
    }
}
