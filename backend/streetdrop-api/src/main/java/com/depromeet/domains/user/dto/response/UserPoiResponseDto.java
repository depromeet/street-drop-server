package com.depromeet.domains.user.dto.response;

import com.depromeet.domains.user.dao.UserItemPointDao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public record UserPoiResponseDto(List<UserPoiDto> poi) {
    @Builder
    @Schema(description = "Point Of Interest, 좌표를 찍기 위한 관심 지점")
    public record UserPoiDto(
            @Schema(description = "아이템 아이디", example = "1")
            long itemId,
            @Schema(description = "앨범 Thumbnail 주소", example = "/album/1/1.jpg")
            String albumCover,
            @Schema(description = "위도", example = "37.123456")
            double latitude,
            @Schema(description = "경도", example = "127.123456")
            double longitude
    ) {
        public static UserPoiDto from(UserItemPointDao userItemPointDao) {
            return builder()
                    .itemId(userItemPointDao.getId())
                    .albumCover(userItemPointDao.getAlbumThumbnail())
                    .latitude(userItemPointDao.getPoint().getY())
                    .longitude(userItemPointDao.getPoint().getX())
                    .build();
        }
    }
}
