package com.microservice.controller;

import com.microservice.exception.BadRequestException;
import com.microservice.service.SongService;
import com.microservice.util.IdValidator;
import org.apache.tika.exception.TikaException;
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
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping(consumes = "audio/mpeg", produces = "application/json")
    public ResponseEntity<Map<String, String>> createSong(@RequestBody byte[] song) throws TikaException, IOException, SAXException {
        return ResponseEntity.ok(Map.of("id", songService.createSong(song).getId().toString()));
    }

    @GetMapping(value = "/{id}", produces = "audio/mpeg")
    public ResponseEntity<byte[]> getSong(@PathVariable Long id) throws BadRequestException {
        IdValidator.validateId(id);
        return ResponseEntity.ok(songService.getSong(id).getData());
    }

    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Map<String, List<Long>>> deleteSong(@RequestParam(name = "id") String id) throws BadRequestException {
        IdValidator.validateCsv(id);
        return ResponseEntity.ok(Map.of("ids", songService.deleteAllSong(id)));
    }
}
