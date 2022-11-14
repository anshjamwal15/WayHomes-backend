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
import com.org.dumper.utils.RegexUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final PropertyRepository propertyRepository;

    private final UserRepository userRepository;

    private final FavRepository favRepository;

    public void changeOrAddFavProperty(String email, Long propertyId) {

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id : " + propertyId));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email : " + email));

        Optional<FavProperties> favProperties = favRepository.findByUsersIdAndPropertyId(user.getId(), propertyId);

        if (favProperties.isPresent()) {
            favRepository.delete(favProperties.get());
        } else {
            FavProperties properties = new FavProperties();
            properties.setProperty(property);
            properties.setUsers(user);
            favRepository.save(properties);
        }
    }

    public List<FavPropertiesDto> getFavProperties(Long userId) {

        List<FavProperties> propertiesList = favRepository.findAllByUsersId(userId);

        return ObjectMapperUtils.mapAll(propertiesList, FavPropertiesDto.class);

    }

    public UserDto getUserProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email :" + email));

        return ObjectMapperUtils.map(user, UserDto.class);
    }

    public UserDto updateUserProfile(UserProfileRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email :" + email));

        if (RegexUtils.isValidEmail(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        if (RegexUtils.isValidUsername(request.getUsername())) {
            user.setUsername(request.getUsername());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }

        userRepository.save(user);

        return ObjectMapperUtils.map(user, UserDto.class);
    }

    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email :" + email));

        return ObjectMapperUtils.map(user, UserDto.class);
    }
}
