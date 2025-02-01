package com.microservice.service;

import com.microservice.model.SongMetadata;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SongMetadataExtractor {

    @Autowired
    private SongMetadataMapper songMetadataMapper;

    public SongMetadata getSongMetadata(byte[] song, Long id) throws TikaException, IOException, SAXException {
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();
        Mp3Parser Mp3Parser = new  Mp3Parser();
        InputStream inputstream = new ByteArrayInputStream(song);

        Mp3Parser.parse(inputstream, handler, metadata, pcontext);

        return songMetadataMapper.getSongMetadata(metadata, id);
    }
}
