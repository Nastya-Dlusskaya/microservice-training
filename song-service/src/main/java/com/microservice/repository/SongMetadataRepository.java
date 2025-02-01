package com.microservice.repository;

import com.microservice.model.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMetadataRepository extends JpaRepository<SongMetadata, Long> {
    SongMetadata findBySongId(Long songId);
    boolean existsBySongId(Long songId);
    SongMetadata deleteBySongIdIn(List<Long> songId);
    SongMetadata deleteBySongId(Long songId);
}
