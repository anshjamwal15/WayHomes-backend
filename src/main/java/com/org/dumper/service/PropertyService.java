package com.org.dumper.service;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.mapper.PropertyMapper;
import com.org.dumper.model.Property;
import com.org.dumper.model.PropertyImages;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.repository.PropertyImagesRepository;
import com.org.dumper.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyImagesRepository propertyImagesRepository;

    private final PropertyMapper propertyMapper;

    public String createProperty(MultipartFile[] files, PropertyRequest request) {

        Property newProperty = new Property();

        newProperty.setSqFeet(request.getSqFeet());
        newProperty.setBedrooms(request.getBedrooms());
        newProperty.setBathrooms(request.getBathrooms());
        newProperty.setGarages(request.getGarages());
        newProperty.setPrice(request.getPrice());
        newProperty.setDescription(request.getDescription());
        newProperty.setAddress(request.getAddress());

        propertyRepository.save(newProperty);

        Arrays.asList(files).stream().forEach(file -> {

            PropertyImages images = new PropertyImages();

            try {
                images.setData(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.setContentType(file.getContentType());
            images.setName(file.getName());
            images.setSize(file.getSize());
            images.setProperty(newProperty);
            propertyImagesRepository.save(images);

        });

        return "Property created Successfully";

    }

    public Page<Property> getAllProperty() {

        Pageable pageable = PageRequest.of(0, 10);

        return propertyRepository.findAll(pageable);
    }

    public PropertyDto getPropertyById(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id :" + propertyId ));

        return propertyMapper.toEntity(property);

    }


}
