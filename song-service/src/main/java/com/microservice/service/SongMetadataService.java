package com.microservice.service;

import com.microservice.model.SongMetadata;
import com.microservice.repository.SongMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SongMetadataService {

    @Autowired
    private SongMetadataRepository songMetadataRepository;

    public SongMetadata createSongMetadata(SongMetadata song) {
        return songMetadataRepository.save(song);
    }

    public SongMetadata getSongMetadataBySongId(Long songId) {
        return songMetadataRepository.findBySongId(songId);
    }

    public SongMetadata getSongMetadata(Long id) {
        return songMetadataRepository.findById(id).orElse(null);
    }

    public List<Long> deleteSongMetadata(Long[] ids) {
        List<Long> longStream = Arrays.stream(ids).filter(id -> songMetadataRepository.existsBySongId(id)).toList();
        longStream.forEach(songMetadataRepository::deleteBySongId);
        return longStream;
    }

    public Long deleteSongMetadata(Long id) {
        songMetadataRepository.deleteBySongId(id);
        return id;
    }
}
