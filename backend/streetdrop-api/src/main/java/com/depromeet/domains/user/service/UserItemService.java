package com.depromeet.domains.user.service;

import com.depromeet.common.dto.InfiniteScrollMetaResponseDto;
import com.depromeet.common.dto.InfiniteScrollResponseDto;
import com.depromeet.domains.item.dto.response.ItemGroupByDateResponseDto;
import com.depromeet.domains.item.dto.response.ItemGroupResponseDto;
import com.depromeet.domains.item.dto.response.ItemLocationResponseDto;
import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLike;
import com.depromeet.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.depromeet.util.WeekUtil.getWeekString2Int;
import static com.depromeet.util.WeekUtil.getWeeksAgo;

@Service
@RequiredArgsConstructor
public class UserItemService {

    private final ItemRepository itemRepository;
    private final ItemLikeRepository itemLikeRepository;


    @Getter
    @AllArgsConstructor
    private static class ItemGroupWithDateResponseDto {
        private String date;
        private ItemGroupResponseDto itemGroupResponseDto;
    }

    @Transactional(readOnly = true)
    public InfiniteScrollResponseDto<?, ?> getDropItems(User user, long nextCursor) {
        List<Item> itemList = itemRepository.findByUserId(user.getId(), nextCursor);
        var itemGroupByDateResponseDtoList =
                itemList.stream()
                        .map(
                                item -> {
                                    User dropUser = item.getUser();
                                    UserResponseDto userResponseDto = new UserResponseDto(dropUser);
                                    ItemLocationResponseDto itemLocationResponseDto = new ItemLocationResponseDto(item.getItemLocation().getName());
                                    MusicResponseDto musicResponseDto = new MusicResponseDto(item.getSong());
                                    ItemGroupResponseDto itemGroupResponseDto = new ItemGroupResponseDto(
                                            item.getId(),
                                            userResponseDto,
                                            itemLocationResponseDto,
                                            musicResponseDto,
                                            item.getContent(),
                                            item.getCreatedAt(),
                                            item.getItemLikeCount()
                                    );
                                    String date = getWeeksAgo(item.getCreatedAt());
                                    return new ItemGroupWithDateResponseDto(date, itemGroupResponseDto);
                                })
                        .collect(
                                Collectors.groupingBy(
                                        ItemGroupWithDateResponseDto::getDate,
                                        Collectors.mapping(ItemGroupWithDateResponseDto::getItemGroupResponseDto, Collectors.toList())
                                )
                        )
                        .entrySet()
                        .stream()
                        .map(entry -> new ItemGroupByDateResponseDto(entry.getKey(), entry.getValue()))
                        .sorted(Comparator.comparingInt(dto -> getWeekString2Int(dto.date())))
                        .toList();

        var meta = new InfiniteScrollMetaResponseDto(itemList.size(), -1);

        return new InfiniteScrollResponseDto<>(itemGroupByDateResponseDtoList, meta);
    }

    @Transactional(readOnly = true)
    public InfiniteScrollResponseDto<?, ?> getLikedItems(User user, long nextCursor) {
        List<ItemLike> itemLikeList = itemLikeRepository.findByUserId(user.getId(), nextCursor);
        List<ItemGroupByDateResponseDto> itemGroupByDateResponseDtoList =
                itemLikeList.stream().map(
                        itemLike -> {
                            Item item = itemLike.getItem();
                            User dropUser = item.getUser();
                            UserResponseDto userResponseDto = new UserResponseDto(dropUser);
                            ItemLocationResponseDto itemLocationResponseDto = new ItemLocationResponseDto(item.getItemLocation().getName());
                            ItemGroupResponseDto itemGroupResponseDto = new ItemGroupResponseDto(
                                    item.getId(),
                                    userResponseDto,
                                    itemLocationResponseDto,
                                    new MusicResponseDto(item),
                                    item.getContent(),
                                    item.getCreatedAt(),
                                    item.getItemLikeCount()
                            );
                            String date = getWeeksAgo(itemLike.getCreatedAt());
                            return new ItemGroupWithDateResponseDto(date, itemGroupResponseDto);
                        })
                        .collect(
                                Collectors.groupingBy(
                                        ItemGroupWithDateResponseDto::getDate,
                                        Collectors.mapping(ItemGroupWithDateResponseDto::getItemGroupResponseDto, Collectors.toList())
                                )
                        )
                        .entrySet()
                        .stream()
                        .map(entry -> new ItemGroupByDateResponseDto(entry.getKey(), entry.getValue()))
                        .sorted(Comparator.comparingInt(dto -> getWeekString2Int(dto.date())))
                        .toList();
        var meta = new InfiniteScrollMetaResponseDto(itemLikeList.size(), -1);

        return new InfiniteScrollResponseDto<>(itemGroupByDateResponseDtoList, meta);
    }

}