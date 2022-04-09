package com.org.dumper.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PropertyRequest {

    @NotBlank
    private Long sqFeet;

    @NotBlank
    private Long bedrooms;

    private Long bathrooms;

    private Long garages;

    @NotBlank
    private Long price;

    private String description;

    @NotBlank
    private String address;

    @NotBlank
    private Long userId;

}
