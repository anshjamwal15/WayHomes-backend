package com.org.dumper.dto;

import lombok.Data;

import java.util.List;

@Data
public class FavPropertiesDto {

    public boolean favorite;

//    private UserDto userDto;

    public List<PropertyDto> propertyDto;

}
