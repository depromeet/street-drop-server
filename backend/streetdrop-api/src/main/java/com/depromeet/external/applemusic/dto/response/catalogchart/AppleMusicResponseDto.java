package com.depromeet.external.applemusic.dto.response.catalogchart;

import java.util.List;

public class AppleMusicResponseDto {
    public Results results;
    public Meta meta;

    public static class Results {
        public List<Song> songs;
    }

    public static class Song {
        public String chart;
        public String name;
        public String orderId;
        public String next;
        public List<SongData> data;
        public String href;
    }

    public static class SongData {
        public String id;
        public String type;
        public String href;
        public SongAttributes attributes;
    }

    public static class SongAttributes {
        public String albumName;
        public List<String> genreNames;
        public int trackNumber;
        public int durationInMillis;
        public String releaseDate;
        public String isrc;
        public Artwork artwork;
        public String url;
        public PlayParams playParams;
        public int discNumber;
        public boolean hasLyrics;
        public boolean isAppleDigitalMaster;
        public String name;
        public List<Preview> previews;
        public String artistName;
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

    public static class Preview {
        public String url;
    }

    public static class Meta {
        public MetaResults results;
    }

    public static class MetaResults {
        public List<String> order;
    }
}
