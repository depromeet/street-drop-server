package com.depromeet.external.applemusic.dto.response.catalogchart;

import java.util.List;

public class AppleMusicAlbumChartResponseDto {
    public Results results;
    public Meta meta;

    public static class Results {
        public List<Album> albums;
    }

    public static class Album {
        public String chart;
        public String name;
        public String orderId;
        public String next;
        public List<AlbumData> data;
        public String href;
    }

    public static class AlbumData {
        public String id;
        public String type;
        public String href;
        public AlbumAttributes attributes;
    }

    public static class AlbumAttributes {
        public String copyright;
        public List<String> genreNames;
        public String releaseDate;
        public boolean isMasteredForItunes;
        public String upc;
        public Artwork artwork;
        public PlayParams playParams;
        public String url;
        public String recordLabel;
        public boolean isCompilation;
        public int trackCount;
        public boolean isSingle;
        public String name;
        public String artistName;
        public EditorialNotes editorialNotes;
        public boolean isComplete;
    }

    public static class Artwork {
        public int width;
        public int height;
        public String url;
        public String bgColor;
        public String textColor1;
        public String textColor2;
        public String textColor3;
        public String textColor4;
    }

    public static class PlayParams {
        public String id;
        public String kind;
    }

    public static class EditorialNotes {
        public String shortNote;
    }

    public static class Meta {
        public MetaResults results;
    }

    public static class MetaResults {
        public List<String> order;
    }
}
