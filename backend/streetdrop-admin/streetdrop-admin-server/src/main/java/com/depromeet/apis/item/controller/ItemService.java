package com.depromeet.apis.item.controller;

import com.depromeet.apis.common.dto.PageMetaData;
import com.depromeet.apis.item.dto.ItemAllResponseDto;
import com.depromeet.apis.item.dto.ItemResponseDto;
import com.depromeet.apis.item.repository.ItemRepository;
import com.depromeet.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemAllResponseDto getAllItems(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                itemPage.getNumber(),
                itemPage.getSize(),
                (int) itemPage.getTotalElements(),
                itemPage.getTotalPages()
        );
        List<ItemResponseDto> items = itemPage.getContent()
                .stream()
                .map((item) ->
                {
                    String itemLocationName;
                    try {
                        itemLocationName = item.getItemLocation().getVillageArea().getVillageName();
                    } catch (NullPointerException e){
                        itemLocationName = "";
                    }

                    return ItemResponseDto.builder()
                            .id(item.getId())
                            .songName(item.getSong().getName())
                            .artistName(item.getSong().getAlbum().getArtist().getName())
                            .dropLocationName(itemLocationName)
                            .userNickname(item.getUser().getNickname())
                            .comment(item.getContent())
                            .createdAt(item.getCreatedAt())
                            .build();
                }).toList();



        return new ItemAllResponseDto(items, pageMetaData);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
