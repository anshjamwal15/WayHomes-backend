package com.org.dumper.mapper;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    Property toEntity(PropertyDto propertyDto);

    PropertyDto toDto(Property property);

    List<Property> map(List<PropertyDto> propertyDtoList);

//    Page<PropertyDto> toPageDto(Page<Property> properties);
}
