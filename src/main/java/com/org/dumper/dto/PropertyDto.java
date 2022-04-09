package com.org.dumper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PropertyDto {

    private Long id;

    public Long sqFeet;

    public Long bedrooms;

    public Long bathrooms;

    public Long garages;

    public Long price;

    public String description;

    public String address;

    public UserDto userDto;

    private Set<PropertyImagesDto> propertyImages;

    public Set<UserDto> isFavourite;
}
