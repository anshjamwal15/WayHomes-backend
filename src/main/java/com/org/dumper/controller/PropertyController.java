package com.org.dumper.controller;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.model.Property;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/property")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/create-property")
    public ResponseEntity<String> createProperty(
            @RequestParam MultipartFile[] files, PropertyRequest request
    ) {
        try {
            propertyService.createProperty(files, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("Property created SuccessFully");
    }

    @GetMapping("/all")
    public Page<Property> getAllProperty() {
        return propertyService.getAllProperty();
    }

    @GetMapping(value = "/{propertyId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long propertyId) {

        PropertyDto propertyDto = propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(propertyDto);
    }
}
