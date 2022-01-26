package com.org.dumper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PropertyDto {

    public Long sqFeet;

    public Long bedrooms;

    public Long bathrooms;

    public Long garages;

    public Long price;

    public String description;

    private Set<PropertyImagesDto> propertyImages;

    public Set<UserDto> isFavourite;
}
