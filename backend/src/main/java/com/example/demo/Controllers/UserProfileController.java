package com.example.demo.Controllers;

import com.example.demo.DTOs.AuthUserProfile;
import com.example.demo.DTOs.UserCredentials;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfile;
import com.example.demo.Services.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userProfile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileOutput> getUsersInformation(HttpServletRequest request)
    {
        log.warn("Getting users information");
        return userProfileService.getUserInformation(request);
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileOutput> registerUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserCredentials userCredentials)
    {
        log.info("Register user");
        return userProfileService.registerUser(request, response, userCredentials);
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserProfileOutput> logInUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserCredentials userCredentials)
    {
        log.info("Log in user");
        return userProfileService.logInUser(request, response, userCredentials);
    }

    @DeleteMapping
    public ResponseEntity<UserProfileOutput> deleteUser(HttpServletResponse response, @AuthenticationPrincipal AuthUserProfile usersProfile)
    {
        log.warn("Deleting the user");
        return userProfileService.deleteUser(response, usersProfile);
    }
}
