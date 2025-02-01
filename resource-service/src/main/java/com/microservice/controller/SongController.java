package com.microservice.controller;

import com.microservice.model.Song;
import com.microservice.service.SongService;
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

@RestController
@RequestMapping("/resources")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping(consumes = "audio/mpeg")
    public ResponseEntity<Song> createSong(@RequestBody byte[] song) throws TikaException, IOException, SAXException {
        return ResponseEntity.ok(songService.createSong(song));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSong(id));
    }

    @DeleteMapping
    public ResponseEntity<List<Long>> deleteSong(@RequestParam(name = "id") Long[] id) {
        return ResponseEntity.ok(songService.deleteAllSong(id));
    }
}
