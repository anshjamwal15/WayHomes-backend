package com.org.dumper.service;

import com.org.dumper.dto.PropertyDto;
import com.org.dumper.dto.PropertyImagesDto;
import com.org.dumper.dto.UserDto;
import com.org.dumper.model.Property;
import com.org.dumper.model.PropertyImages;
import com.org.dumper.model.User;
import com.org.dumper.payload.request.PropertyRequest;
import com.org.dumper.repository.PropertyImagesRepository;
import com.org.dumper.repository.PropertyRepository;
import com.org.dumper.repository.UserRepository;
import com.org.dumper.utils.HelperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyImagesRepository propertyImagesRepository;

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final FileService fileService;

    private final HelperUtils helperUtils;

    public String createProperty(MultipartFile[] files, PropertyRequest request) {

        Property newProperty = new Property();

        newProperty.setSqFeet(request.getSqFeet());
        newProperty.setBedrooms(request.getBedrooms());
        newProperty.setBathrooms(request.getBathrooms());
        newProperty.setGarages(request.getGarages());
        newProperty.setPrice(request.getPrice());
        newProperty.setDescription(request.getDescription());
        newProperty.setAddress(request.getAddress());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceAccessException("User not found with id: " + request.getUserId()));

        // TODO Check user as buyer or seller
//        if (user.getRoles() == )

        newProperty.setUser(user);

        propertyRepository.save(newProperty);

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new IllegalStateException("Cannot upload empty file");
            }

            if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                    IMAGE_BMP.getMimeType(),
                    IMAGE_GIF.getMimeType(),
                    IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
                throw new IllegalStateException("File uploaded is not an image");
            }

            String path = String.format("%s%s", "dumper-storage", UUID.randomUUID());

            File convertedFile = helperUtils.multipartFileToFile(file);
            fileService.uploadFile(convertedFile);

            PropertyImages images = PropertyImages.builder()
                    .path(path)
                    .build();
            propertyImagesRepository.save(images);
        }

        return "Property created Successfully";

    }

    public String addPropertyImage(Long propertyId, MultipartFile[] request) {

        propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id :" + propertyId));

        for (MultipartFile file : request) {
            if (file.isEmpty()) {
                throw new IllegalStateException("Cannot upload empty file");
            }

            if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                    IMAGE_BMP.getMimeType(),
                    IMAGE_GIF.getMimeType(),
                    IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
                throw new IllegalStateException("File uploaded is not an image");
            }

            String path = String.format("%s%s", "dumper-storage", UUID.randomUUID());

            File convertedFile = helperUtils.multipartFileToFile(file);
            fileService.uploadFile(convertedFile);

            PropertyImages images = PropertyImages.builder()
                    .path(path)
                    .contentType(file.getContentType())
                    .build();
            propertyImagesRepository.save(images);
        }

        return "Image added Successfully";
    }

    public Page<PropertyDto> getAllProperty() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Property> properties = propertyRepository.findAll(pageable);

        List<PropertyDto> propertyDtoList = new ArrayList<>();

        for (Property property : properties) {

            PropertyDto scopeDto = new PropertyDto();
            // Property info
            scopeDto.setId(property.getId());
            scopeDto.setAddress(property.getAddress());
            scopeDto.setBathrooms(property.getBathrooms());
            scopeDto.setBedrooms(property.getBedrooms());
            scopeDto.setDescription(property.getDescription());
            scopeDto.setGarages(property.getGarages());
            scopeDto.setPrice(property.getPrice());
            scopeDto.setSqFeet(property.getSqFeet());
            // User
            UserDto user = new UserDto();
            user.setEmail(property.getUser().getEmail());
            user.setFirstName(property.getUser().getFirstName());
            user.setLastName(property.getUser().getLastName());
            user.setUsername(property.getUser().getUsername());
            scopeDto.setUser(user);
            // Property images
            Set<PropertyImagesDto> imagesDtoSet = new HashSet<>();
            for (PropertyImages images : property.getPropertyImages()) {
                PropertyImagesDto imagesDto = new PropertyImagesDto();
                imagesDto.setData(images.getData());
                imagesDto.setName(images.getName());
                imagesDto.setPath(images.getPath());
                imagesDto.setContentType(images.getContentType());
                imagesDto.setSize(images.getSize());
                imagesDtoSet.add(imagesDto);
            }
            scopeDto.setPropertyImages(imagesDtoSet);
            propertyDtoList.add(scopeDto);
        }
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), propertyDtoList.size());

        return new PageImpl<>(propertyDtoList.subList(start, end), pageable, propertyDtoList.size());
    }

    public PropertyDto getPropertyById(Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id :" + propertyId));

        return mapper.map(property, PropertyDto.class);

    }

    public String deleteById(Long propertyId) {

        Property property = propertyRepository.getById(propertyId);

        List<PropertyImages> imagesList = propertyImagesRepository.findAllByProperty(property);

        if (!imagesList.isEmpty()) {
            for (PropertyImages images : imagesList) {
                propertyImagesRepository.deleteById(images.getId());
            }
        }
        propertyRepository.deleteById(property.getId());

        return "Property successfully deleted with id: " + propertyId;
    }


}
