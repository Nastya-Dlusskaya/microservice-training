package com.microservice.controller;

import com.microservice.model.SongMetadata;
import com.microservice.service.SongMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongMetadataController {

    @Autowired
    private SongMetadataService songMetadataService;

    @PostMapping
    public ResponseEntity<SongMetadata> createSong(@RequestBody SongMetadata song)  {
        SongMetadata song1 = songMetadataService.createSongMetadata(song);
        return ResponseEntity.ok(song1);
    }

    @GetMapping
    private ResponseEntity<SongMetadata> getSongMetadataBySongId(@Param("songId") Long songId) {
        return ResponseEntity.ok(songMetadataService.getSongMetadataBySongId(songId));
    }

    @GetMapping("/{id}")
    private ResponseEntity<SongMetadata> getSongMetadata(@PathVariable Long id) {
        return ResponseEntity.ok(songMetadataService.getSongMetadata(id));
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteResource(@RequestParam(name = "id") Long[] id) {
        return ResponseEntity.ok(songMetadataService.deleteSongMetadata(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteResource(@PathVariable Long id) {
        return ResponseEntity.ok(songMetadataService.deleteSongMetadata(id));
    }
}
