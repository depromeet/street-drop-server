package com.depromeet.domains.village.service;

import com.depromeet.domains.village.dto.ItemCountPerVillageResponseDto;
import com.depromeet.domains.village.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VillageService {

    private final VillageRepository villageRepository;

    public List<ItemCountPerVillageResponseDto> getVillageItemCount() {
        return villageRepository.countItemByVillage().stream().map(
                row -> {
                    String villageName = (String) row[0];
                    Long itemCount = (Long) row[1];
                    return new ItemCountPerVillageResponseDto(villageName, itemCount);
                }
        ).toList();
    }

    public List<ItemCountPerVillageResponseDto> getVillageItemCountRecent(){
        return villageRepository.countItemByVillageIn3Days().stream().map(
                row -> {
                    String villageName = (String) row[0];
                    Long itemCount = (Long) row[1];
                    return new ItemCountPerVillageResponseDto(villageName, itemCount);
                }
        ).toList();
    }

}
