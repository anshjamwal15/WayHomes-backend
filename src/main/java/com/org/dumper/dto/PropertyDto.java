package com.org.dumper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PropertyDto {

    private Long sqFeet;

    private Long bedrooms;

    private Long bathrooms;

    private Long garages;

    private Long price;

    private String description;

    private Set<PropertyImagesDto> propertyImages;

    private Set<UserDto> isFavourite;
}
