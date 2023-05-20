package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ItemService {

    private static final Double RELATIVE_DISTANCE = 500.0;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Page<ItemDetailResponseDto> findNearItems(Double longitude, Double latitude, Pageable pageable) {
        var items = itemRepository.findNearItems(longitude, latitude, RELATIVE_DISTANCE, pageable);
        var response = new PageImpl<>(
                items.stream().map(ItemDetailResponseDto::new).toList(),
                pageable,
                items.getTotalElements());
        return response;
    }
    
}
