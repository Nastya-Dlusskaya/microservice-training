package com.microservice.service;

import com.microservice.model.SongMetadata;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Component;

@Component
public class SongMetadataMapper {

    public SongMetadata getSongMetadata(Metadata metadata, Long id) {
        SongMetadata songMetadata = new SongMetadata();
        songMetadata.setSongId(id);
        songMetadata.setName(metadata.get("dc:title"));
        songMetadata.setArtist(metadata.get("xmpDM:artist"));
        songMetadata.setAlbum(metadata.get("xmpDM:album"));
        songMetadata.setDuration(getDuration(metadata.get("xmpDM:duration")));
        songMetadata.setYear(metadata.get("xmpDM:releaseDate"));
        return songMetadata;
    }

    private String getDuration(String duration) {
        String[] durationParts = duration.split("\\.");
        long seconds = Long.parseLong(durationParts[0]);
        return addZero(seconds / 60) + ":" + addZero(seconds % 60).substring(0, 2);
    }

    private String addZero(long seconds) {
        return seconds < 10 ? "0" + seconds : String.valueOf(seconds);
    }
}
