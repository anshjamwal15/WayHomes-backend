package com.org.dumper.mapper;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property toEntity(PropertyDto propertyDto);

    PropertyDto toDto(Property property);

    List<Property> map(List<PropertyDto> propertyDtoList);

//    Page<PropertyDto> toPageDto(Page<Property> properties);
}
