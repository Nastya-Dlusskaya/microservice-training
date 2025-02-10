package com.microservice.service;

import com.microservice.client.SongMetadataClient;
import com.microservice.model.Song;
import com.microservice.model.SongMetadata;
import com.microservice.repository.SongRepository;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMetadataClient songMetadataClient;

    @Autowired
    private SongMetadataExtractor songMetadataExtractor;

    public Song getSong(Long id) throws NoSuchElementException {
        return songRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Song not found"));
    }

    @Transactional
    public Song createSong(byte[] song) throws IOException, TikaException, SAXException {
        Song saved = songRepository.save(new Song(song));

        SongMetadata songMetadata = songMetadataExtractor.getSongMetadata(song, saved.getId());

        songMetadataClient.createSongMetadata(songMetadata);

        return saved;
    }

    @Transactional
    public List<Long> deleteAllSong(String ids) {
        List<Long> list = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .filter(id -> songRepository.existsById(id))
                .toList();

        list.forEach(id -> {
            songRepository.deleteById(id);
            songMetadataClient.deleteSongMetadata(id);
        });
        return list;
    }
}
