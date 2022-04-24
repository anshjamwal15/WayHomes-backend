package com.org.dumper.mapper;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    PropertyDto toDto(Property property);
}
