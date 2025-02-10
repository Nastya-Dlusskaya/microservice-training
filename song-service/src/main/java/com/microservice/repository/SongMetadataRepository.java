package com.microservice.repository;

import com.microservice.model.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongMetadataRepository extends JpaRepository<SongMetadata, Long> {
    Optional<SongMetadata> findBySongId(Long songId);
    Long deleteBySongId(Long songId);
    boolean existsBySongId(Long id);
}
