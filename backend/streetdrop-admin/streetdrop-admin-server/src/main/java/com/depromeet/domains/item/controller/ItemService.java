package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.domains.item.dto.ItemAllResponseDto;
import com.depromeet.domains.item.dto.ItemDetailResponseDto;
import com.depromeet.domains.item.dto.ItemResponseDto;
import com.depromeet.domains.item.dto.detail.ItemBasicInfoDto;
import com.depromeet.domains.item.dto.detail.ItemLocationInfoDto;
import com.depromeet.domains.item.dto.detail.ItemMusicInfoDto;
import com.depromeet.domains.item.dto.detail.ItemUserInfoDto;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.item.Item;
import com.depromeet.music.genre.Genre;
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


    @Transactional(readOnly = true)
    public ItemDetailResponseDto getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이템입니다.")
        );

        ItemBasicInfoDto itemBasicInfoDto = new ItemBasicInfoDto(
                item.getId(),
                item.getItemLikeCount(),
                item.getCreatedAt()
        );

        ItemUserInfoDto itemUserInfoDto = new ItemUserInfoDto(
                item.getUser().getId(),
                item.getUser().getNickname(),
                item.getUser().getIdfv()
        );

        ItemMusicInfoDto itemMusicInfoDto = new ItemMusicInfoDto(
                item.getSong().getName(),
                item.getSong().getAlbum().getArtist().getName(),
                item.getSong().getAlbum().getName(),
                item.getSong().getAlbum().getAlbumCover().getAlbumImage(),
                item.getSong().getGenres().stream().map(Genre::getName).toList().toString()
        );

        ItemLocationInfoDto itemLocationInfoDto = new ItemLocationInfoDto(
            item.getItemLocation().getPoint().getY(),
            item.getItemLocation().getPoint().getX(),
            item.getItemLocation().getVillageArea().getVillageName()
        );

        return new ItemDetailResponseDto(
                itemBasicInfoDto,
                itemUserInfoDto,
                itemMusicInfoDto,
                itemLocationInfoDto
        );

    }
}
