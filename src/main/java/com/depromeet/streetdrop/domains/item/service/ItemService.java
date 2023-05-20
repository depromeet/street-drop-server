package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private static final Double RELATIVE_DISTANCE = 500.0;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ItemDetailResponseDto> findNearItems(Double longitude, Double latitude) {
        var items = itemRepository.findNearItems(longitude, latitude, RELATIVE_DISTANCE);
        var response = items.stream()
                .map(ItemDetailResponseDto::new)
                .toList();
        return response;
    }
    
}
