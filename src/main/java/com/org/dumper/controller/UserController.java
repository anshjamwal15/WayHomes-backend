package com.org.dumper.controller;

import com.org.dumper.dto.FavPropertiesDto;
import com.org.dumper.dto.UserDto;
import com.org.dumper.model.FavProperties;
import com.org.dumper.payload.request.UserProfileRequest;
import com.org.dumper.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/like-dislike/{userId}/{propertyId}")
    public ResponseEntity<String> addFavProperty(
            @PathVariable Long propertyId, @PathVariable Long userId,
            @RequestParam boolean isFav
    ) {
        userService.addFavProperty(userId, propertyId, isFav);

        return ResponseEntity.ok("User successfully changed value to : " + isFav);
    }

    @PostMapping("/update-profile/{userId}")
    public ResponseEntity<UserDto> updateUserProfile(
            UserProfileRequest request, @PathVariable Long userId
    ) {
        UserDto userDto = userService.updateUserProfile(request, userId);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/get-user-info/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {

        UserDto user = userService.getUserProfile(userId);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/liked-properties/{userId}")
    public ResponseEntity<List<FavPropertiesDto>> getFavProperties(@PathVariable Long userId) {

        List<FavPropertiesDto> propertiesList = userService.getFavProperties(userId);

        return ResponseEntity.ok(propertiesList);
    }
}
