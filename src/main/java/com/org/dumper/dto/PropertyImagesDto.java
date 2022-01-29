package com.org.dumper.dto;

import lombok.Data;

@Data
public class PropertyImagesDto {

//    private byte[] data;

    private String path;

    private String contentType;

    private String name;

    private Long size;
}
