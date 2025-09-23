package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Services.UserProfileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userProfile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<UserProfileOutput> logTheUser(/*@Valid*/ @RequestBody CreateUserProfile  createUserProfile)
    {
        return userProfileService.createUsersInDataBase(createUserProfile);
    }

    @GetMapping
    public ResponseEntity<?> createCookies()
    {
        log.warn("createCookies");
        return ResponseEntity.ok().build();
    }
}
