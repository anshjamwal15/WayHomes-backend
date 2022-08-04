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
    public String address;
    public UserDto user;
    public Set<UserDto> isFavourite;
    private Long id;
    private Set<PropertyImagesDto> propertyImages;
}
