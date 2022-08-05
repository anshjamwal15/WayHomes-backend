package com.org.dumper.controller;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/property")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/create-property")
    public ResponseEntity<String> createProperty(
            @RequestParam MultipartFile[] files, PropertyRequest request
    ) throws IOException {
        propertyService.createProperty(files, request);
        return ResponseEntity.ok().body("Property created SuccessFully");
    }

    @PostMapping("/add-image/{propertyId}")
    public ResponseEntity<String> addImage(
            @RequestParam MultipartFile[] files, @PathVariable Long propertyId
    ) {
        propertyService.addPropertyImage(propertyId, files);

        return ResponseEntity.ok("Image added Successfully");
    }

    @GetMapping("/all")
    public Page<PropertyDto> getAllProperty() {
        return propertyService.getAllProperty();
    }

    @GetMapping(value = "/{propertyId}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long propertyId) {

        PropertyDto property = propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(property);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteById(@PathVariable Long propertyId) {
        String success = propertyService.deleteById(propertyId);
        return ResponseEntity.ok(success);
    }

}
