package com.depromeet.domains.user.service;

import com.depromeet.common.dto.InfiniteScrollMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.domains.item.dao.ItemDao;
import com.depromeet.domains.item.dto.response.ItemGroupByDateResponseDto;
import com.depromeet.domains.item.dto.response.ItemGroupResponseDto;
import com.depromeet.domains.item.dto.response.ItemLocationResponseDto;
import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.item.service.ItemLikeService;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.user.dto.request.ItemOrderType;
import com.depromeet.domains.user.dto.response.UserItemLocationCountDto;
import com.depromeet.domains.user.dto.response.UserPoiResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.util.WeekUtil.getWeeksAgo;

@Service
@RequiredArgsConstructor
public class UserItemService {

    private final ItemRepository itemRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final ItemLocationRepository itemLocationRepository;
    private final ItemLikeService itemLikeService;

    @Transactional(readOnly = true)
    public PaginationResponseDto<?, ?> getDropItems(User user, long nextCursor, ItemOrderType orderType) {
        List<ItemDao> itemList = itemRepository.findByUserId(user.getId(), nextCursor, orderType);
        List<ItemGroupByDateResponseDto> itemGroupByDateResponseDto = itemList
                .stream()
                .map(ItemDao::getWeekAgo)
                .distinct()
                .map(value -> {
                    List<ItemGroupResponseDto> itemGroupResponseDtoList = itemList.stream()
                            .filter(itemDao -> itemDao.getWeekAgo() == value)
                            .map(itemDao -> itemDaotoItemGroupResponseDto(user, itemDao))
                            .toList();
                    return new ItemGroupByDateResponseDto(getWeeksAgo(value), itemGroupResponseDtoList);
                })
                .toList();

        var meta = InfiniteScrollMetaResponseDto
                .builder()
                .totalCount(itemList.size())
                .nextCursor(-1).build();

        return new PaginationResponseDto<>(itemGroupByDateResponseDto, meta);
    }

    @Transactional(readOnly = true)
    public UserPoiResponseDto getDropItemsPoints(User user) {
        var userPoiDtoList = itemLocationRepository.findUserDropItemsPoints(user.getId())
                .stream()
                .map(UserPoiResponseDto.UserPoiDto::from)
                .toList();
        return new UserPoiResponseDto(userPoiDtoList);
    }

    private ItemGroupResponseDto itemDaotoItemGroupResponseDto(User user, ItemDao itemDao) {
        return ItemGroupResponseDto
                .builder()
                .itemId(itemDao.getItemId())
                .user(new UserResponseDto(user))
                .location(new ItemLocationResponseDto(itemDao.getLocationName()))
                .music(
                        MusicResponseDto.builder()
                                .title(itemDao.getSongName())
                                .artist(itemDao.getArtistName())
                                .albumImage(itemDao.getAlbumThumbnail())
                                .genre(List.of())
                                .build()
                )
                .content(itemDao.getContent())
                .createdAt(itemDao.getCreatedAt())
                .itemLikeCount(itemDao.getItemCount())
                .build();
    }


    @Transactional(readOnly = true)
    public PaginationResponseDto<?, ?> getLikedItems(User user, long nextCursor, ItemOrderType itemOrderType) {
        return itemLikeService.getLikedItemsByUser(user, nextCursor, itemOrderType);
    }

    @Transactional(readOnly = true)
    public UserPoiResponseDto getLikedItemsPoints(User user) {
        var userPoiDtoList = itemLikeRepository.findUserLikedItemsPoints(user.getId())
                .stream()
                .map(UserPoiResponseDto.UserPoiDto::from)
                .toList();
        return new UserPoiResponseDto(userPoiDtoList);
    }

    @Transactional(readOnly = true)
    public UserItemLocationCountDto countItemsByLocation(User user, String state, String city) {
        Long count = 0L;
        if (state == null) {
            count = itemLocationRepository.countItems(user.getId());
        }
        else if (city == null) {
            count = itemLocationRepository.countItemsByState(user.getId(), state);
        }
        else {
            count = itemLocationRepository.countItemsByCity(user.getId(), city);
        }
        return new UserItemLocationCountDto(count, state, city);
    }

}