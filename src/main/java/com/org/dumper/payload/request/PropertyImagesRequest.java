package com.org.dumper.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PropertyImagesRequest {

    private MultipartFile images;
}
