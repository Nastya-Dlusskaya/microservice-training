package com.microservice.repository;

import com.microservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Override
    void deleteAllById(Iterable<? extends Long> ids);
}
