package com.depromeet.domains.item.dto.response;

import com.depromeet.domains.item.dao.ItemPointDao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public record PoiResponseDto(List<PoiDto> poi) {
    @Builder
    @Schema(description = "Point Of Interest, 좌표를 찍기위하 관심 지점")
    public record PoiDto(
            @Schema(description = "아이템 아이디", example = "1")
            long itemId,
            @Schema(description = "앨범 Thumbnail 주소", example = "/album/1/1.jpg")
            String albumCover,
            @Schema(description = "위도", example = "37.123456")
            double latitude,
            @Schema(description = "경도", example = "127.123456")
            double longitude) {
        public static PoiDto fromItemPoint(ItemPointDao itemPointDao) {
            return builder()
                    .itemId(itemPointDao.getId())
                    .albumCover(itemPointDao.getAlbumThumbnail())
                    .latitude(itemPointDao.getPoint().getY())
                    .longitude(itemPointDao.getPoint().getX())
                    .build();
        }
    }

}
