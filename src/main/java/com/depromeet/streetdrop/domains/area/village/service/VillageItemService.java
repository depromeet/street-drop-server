package com.depromeet.streetdrop.domains.area.village.service;

import com.depromeet.streetdrop.domains.area.village.repository.VillageAreaRepository;
import com.depromeet.streetdrop.domains.area.village.dto.response.VillageItemsCountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageItemService {

    private final VillageAreaRepository villageAreaRepository;

    public VillageItemsCountResponseDto countItemsByVillage(String villageName) {
        var response = villageAreaRepository.countItemsByVillageName(villageName);
        return new VillageItemsCountResponseDto(response);
    }

}
