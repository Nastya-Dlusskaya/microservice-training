package com.microservice.controller;

import com.microservice.exception.DuplicationException;
import com.microservice.model.SongMetadataDTO;
import com.microservice.service.SongMetadataService;
import com.microservice.util.IdsValidator;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongMetadataController {

    @Autowired
    private SongMetadataService songMetadataService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> createSongMetadata(@RequestBody @Valid SongMetadataDTO songMetadata) throws DuplicationException {
        SongMetadataDTO savedSongMetadata = songMetadataService.createSongMetadata(songMetadata);
        return ResponseEntity.ok(Map.of("id", savedSongMetadata.getId().toString()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    private ResponseEntity<SongMetadataDTO> getSongMetadata(@PathVariable Long id) {
        return ResponseEntity.ok(songMetadataService.getSongMetadata(id));
    }

    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Map<String, List<String>>> deleteResource(@RequestParam(name = "ids") String ids) throws BadRequestException {
        IdsValidator.validate(ids);
        List<String> songMetadataIds = songMetadataService.deleteSongMetadata(ids).stream().map(Object::toString).toList();
        return ResponseEntity.ok(Map.of("ids", songMetadataIds));
    }
}
