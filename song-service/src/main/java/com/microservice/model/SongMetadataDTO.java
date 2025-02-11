package com.microservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SongMetadataDTO {
    private Long id;
    private Long songId;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;
    @NotBlank(message = "Artist is mandatory")
    @Size(min = 1, max = 100, message = "Artist must be between 1 and 100 characters")
    private String artist;
    @NotBlank(message = "Album is mandatory")
    @Size(min = 1, max = 100, message = "Album must be between 1 and 100 characters")
    private String album;
    @NotBlank(message = "Duration is mandatory")
    @Pattern(regexp = "^([0-9]|[0-5][0-9]):[0-5][0-9]$", message = "Duration must be in mm:ss format with leading zeros")
    private String duration;
    @NotBlank(message = "Year is mandatory")
    @Pattern(regexp = "^(19\\d{2}|20[0-9]{2})$", message = "Year must be between 1900 and 2099")
    private String year;

    public SongMetadataDTO() {
    }

    public SongMetadataDTO(Long id, Long songId, String name, String artist, String album, String duration, String year) {
        this.id = id;
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
