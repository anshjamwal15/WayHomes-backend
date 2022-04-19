package com.org.dumper.service;

import com.org.dumper.dto.FavPropertiesDto;
import com.org.dumper.dto.UserDto;
import com.org.dumper.model.FavProperties;
import com.org.dumper.model.Property;
import com.org.dumper.model.User;
import com.org.dumper.payload.request.UserProfileRequest;
import com.org.dumper.repository.FavRepository;
import com.org.dumper.repository.PropertyRepository;
import com.org.dumper.repository.UserRepository;
import com.org.dumper.utils.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;

    private final FavRepository favRepository;

    public String addFavProperty(Long userId, Long propertyId, boolean isFav) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Optional<FavProperties> favProperties = favRepository.findByUsersIdAndPropertyId(userId, propertyId);

        if (favProperties.isPresent()) {
            favProperties.stream().forEach(prop -> {
                prop.setFavorite(isFav);
                favRepository.save(prop);
            });
        } else {
            FavProperties properties = new FavProperties();

            properties.setFavorite(isFav);
            properties.setProperty(property);
            properties.setUsers(user);
            favRepository.save(properties);
        }

        return "User successfully changed value to : " + isFav;
    }

    public List<FavPropertiesDto> getFavProperties(Long userId) {

        List<FavProperties> propertiesList = favRepository.findAllByUsersId(userId);

        return ObjectMapperUtils.mapAll(propertiesList, FavPropertiesDto.class);

    }

    public UserDto getUserProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id :" + userId));

        return ObjectMapperUtils.map(user, UserDto.class);
    }

    public UserDto updateUserProfile(UserProfileRequest request, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id :" + userId));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return ObjectMapperUtils.map(user, UserDto.class);

    }
}
