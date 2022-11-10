package com.org.dumper.controller;

import com.org.dumper.dto.FavPropertiesDto;
import com.org.dumper.dto.UserDto;
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

    @PostMapping("/likedislike/{propertyId}")
    public ResponseEntity<String> addFavProperty(
            @PathVariable Long propertyId, @RequestParam String email
    ) {
        userService.changeOrAddFavProperty(email, propertyId);

        return ResponseEntity.ok("User Changed entity Successfully.");
    }

    @PostMapping("/update-profile/{userId}")
    public ResponseEntity<UserDto> updateUserProfile(
            @RequestBody UserProfileRequest request, @PathVariable Long userId
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

    @GetMapping("/getuser")
    public ResponseEntity<UserDto> getUser(@RequestBody String email) {

        UserDto user = userService.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }
}
