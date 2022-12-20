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

    @PostMapping("/updateprofile")
    public ResponseEntity<UserDto> updateUserProfile(
            @RequestBody UserProfileRequest request, @RequestParam String email
    ) {
        UserDto userDto = userService.updateUserProfile(request, email);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/getuserinfo")
    public ResponseEntity<UserDto> getUserProfile(@RequestParam String email) {

        UserDto user = userService.getUserProfile(email);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/likedproperties")
    public ResponseEntity<List<FavPropertiesDto>> getFavProperties(@RequestParam String email) {

        List<FavPropertiesDto> propertiesList = userService.getFavProperties(email);

        return ResponseEntity.ok(propertiesList);
    }

    @GetMapping("/getuser")
    public ResponseEntity<UserDto> getUser(@RequestBody String email) {

        UserDto user = userService.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }
}
