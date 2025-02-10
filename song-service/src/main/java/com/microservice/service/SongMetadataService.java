package com.microservice.service;

import com.microservice.exception.DuplicationException;
import com.microservice.util.mapper.SongMetadataMapper;
import com.microservice.model.SongMetadataDTO;
import com.microservice.repository.SongMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class SongMetadataService {

    @Autowired
    private SongMetadataMapper mapper;

    @Autowired
    private SongMetadataRepository songMetadataRepository;

    public SongMetadataDTO createSongMetadata(SongMetadataDTO song) throws DuplicationException {
        if (song.getId() != null && songMetadataRepository.existsById(song.getId())) {
            throw new DuplicationException("Song metadata already exists");
        }
        return mapper.toDto(songMetadataRepository.save(mapper.toEntity(song)));
    }

    public SongMetadataDTO getSongMetadata(Long id) throws NoSuchElementException {
        return mapper.toDto(songMetadataRepository.findBySongId(id).orElseThrow(() -> new NoSuchElementException("Song metadata not found")));
    }

    @Transactional
    public List<Long> deleteSongMetadata(String ids) {
        String[] split = ids.split(",");
        return Arrays.stream(split).map(Long::parseLong).map(this::deleteSongMetadata).filter(Objects::nonNull).toList();
    }

    @Transactional
    public Long deleteSongMetadata(Long id) {
        if (!songMetadataRepository.existsBySongId(id)) {
            return null;
        }
        return songMetadataRepository.deleteBySongId(id);
    }
}
