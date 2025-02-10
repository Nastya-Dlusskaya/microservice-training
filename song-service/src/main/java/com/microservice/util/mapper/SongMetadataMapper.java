package com.microservice.util.mapper;

import com.microservice.model.SongMetadata;
import com.microservice.model.SongMetadataDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMetadataMapper {
    SongMetadataDTO toDto(SongMetadata songMetadata);

    SongMetadata toEntity(SongMetadataDTO songMetadataDTO);
}
