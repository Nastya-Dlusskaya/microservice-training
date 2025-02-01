package com.microservice.service;

import com.microservice.client.SongMetadataClient;
import com.microservice.model.Song;
import com.microservice.model.SongMetadata;
import com.microservice.repository.SongRepository;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMetadataClient songMetadataClient;

    @Autowired
    private SongMetadataExtractor songMetadataExtractor;

    public Song getSong(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Transactional
    public Song createSong(byte[] song) throws IOException, TikaException, SAXException {
        Song saved = songRepository.save(new Song(song));

        SongMetadata songMetadata = songMetadataExtractor.getSongMetadata(song, saved.getId());
        songMetadata.setSongId(saved.getId());

        songMetadataClient.createSongMetadata(songMetadata);

        return saved;
    }

    @Transactional
    public List<Long> deleteAllSong(Long[] ids) {
        List<Long> list = Arrays.stream(ids).filter(id -> songRepository.existsById(id)).toList();

        list.forEach(id -> {
            songRepository.deleteById(id);
            songMetadataClient.deleteSongMetadata(id);
        });
        return list;
    }
}
