package com.org.dumper.controller;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.dto.TagDto;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/property")
@AllArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping(path = "/createproperty", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> createProperty(
        @ModelAttribute PropertyRequest request
    ) throws Exception {
        System.out.println(request.getFiles().get(0).getContentType());
        // propertyService.createProperty(request);
        return ResponseEntity.ok().body("Property created SuccessFully");
    }

    @PostMapping("/addimage/{propertyId}")
    public ResponseEntity<String> addImage(
        @RequestParam MultipartFile[] files, @PathVariable Long propertyId
    ) throws Exception {
        propertyService.addPropertyImage(propertyId, files);

        return ResponseEntity.ok("Image added Successfully");
    }

    @GetMapping("/all")
    public Page<PropertyDto> getAllProperty(@RequestParam String email, @RequestParam String tag) {
        return propertyService.getAllProperty(email, tag);
    }

    @GetMapping(value = "/{propertyId}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long propertyId) {

        PropertyDto property = propertyService.getPropertyById(propertyId);

        return ResponseEntity.ok(property);
    }

    @GetMapping("/tags")
    public List<TagDto> getTags() {
        return propertyService.getAllTags();
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteById(@PathVariable Long propertyId) {
        String success = propertyService.deleteById(propertyId);
        return ResponseEntity.ok(success);
    }

}
