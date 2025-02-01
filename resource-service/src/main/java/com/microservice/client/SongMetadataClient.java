package com.microservice.client;

import com.microservice.model.SongMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SongMetadataClient {
    @Value("${song.metadata.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public SongMetadata createSongMetadata(SongMetadata songMetadata) {
        HttpEntity<SongMetadata> requestEntity = new HttpEntity<>(songMetadata);
        return restTemplate.postForEntity(url, requestEntity, SongMetadata.class).getBody();
    }

    public List<SongMetadata> deleteSongMetadata(Long id) {
        HttpEntity<Long> requestEntity = new HttpEntity<>(id);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<List<SongMetadata>>() {}).getBody();
    }
}
