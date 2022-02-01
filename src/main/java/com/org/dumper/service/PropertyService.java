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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyImagesRepository propertyImagesRepository;

    private final PropertyMapper propertyMapper;

    private final Path root = Paths.get("uploads");

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

        for (MultipartFile file : files) {
            PropertyImages images = new PropertyImages();

//            String destination = root.resolve(file.getOriginalFilename());
//
//            File fileP = new File(destination);

            try {
                images.setData(file.getBytes());
                Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.setContentType(file.getContentType());
            images.setName(file.getName());
            images.setSize(file.getSize());
            String path = root.toString()+ "/" + file.getOriginalFilename();
            images.setPath(path);
            images.setProperty(newProperty);
            propertyImagesRepository.save(images);

        }

        return "Property created Successfully";

    }


    public String addPropertyImage(Long propertyId, MultipartFile[] request) {

        propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id :" + propertyId));

        for (MultipartFile file : request) {
            PropertyImages images = new PropertyImages();

            String destination = "F:/Practice-dump/Dumper/assets/images/" + file.getOriginalFilename();

            File fileP = new File(destination);

            try {
                images.setData(file.getBytes());
                file.transferTo(fileP);
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.setContentType(file.getContentType());
            images.setName(file.getName());
            images.setSize(file.getSize());
            images.setPath(destination);
            propertyImagesRepository.save(images);

        }

        return "Image added Successfully";
    }

    public Page<PropertyDto> getAllProperty() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<PropertyDto> property = propertyRepository.findAll(pageable).map(propertyMapper::toDto);

        return property;
    }

    public PropertyDto getPropertyById(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id :" + propertyId));

        return propertyMapper.toDto(property);

    }

    public String deleteById(Long propertyId) {

        propertyImagesRepository.deleteById(propertyId);

        return "Property successfully deleted with id: " + propertyId;
    }


}
