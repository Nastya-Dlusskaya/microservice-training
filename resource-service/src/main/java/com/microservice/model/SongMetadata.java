package com.microservice.model;


public class SongMetadata {
    private Long songId;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String year;

    public SongMetadata() {
    }

    public SongMetadata(Long songId, String name, String artist, String album, String duration, String year) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.year = year;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
