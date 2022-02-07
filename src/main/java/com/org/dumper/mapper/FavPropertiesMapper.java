package com.org.dumper.mapper;

import com.org.dumper.dto.FavPropertiesDto;
import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.FavProperties;
import com.org.dumper.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavPropertiesMapper {

    FavPropertiesMapper INSTANCE = Mappers.getMapper(FavPropertiesMapper.class);

//    FavProperties toEntity(FavPropertiesDto propertiesDto);

    FavPropertiesDto toDto(FavProperties favProperties);

    @Mapping()
    PropertyDto toPropertyDto(Property property);

    List<FavPropertiesDto> map(List<FavProperties> propertiesList);
}
