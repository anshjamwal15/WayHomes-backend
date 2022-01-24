package com.org.dumper.mapper;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.Property;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    PropertyDto toEntity(Property property);

    Property toDto(PropertyDto propertyDto);

    List<Property> map(List<PropertyDto> propertyDtoList);
}
