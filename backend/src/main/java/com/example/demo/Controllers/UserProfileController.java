package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfileInformation;
import com.example.demo.Services.UserProfileService;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/register")
    public ResponseEntity<UserProfileOutput> RegisterUser(@RequestBody CreateUserProfile  createUserProfile, HttpServletResponse response)
    {
        log.info("UserProfileController: register user");
        return userProfileService.registerInDb(createUserProfile, response);
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserProfileOutput> LogInTheUser(@RequestBody CreateUserProfile createUserProfile, HttpServletResponse response)
    {
        log.info("User profile controller: log in");
        return userProfileService.retrieveUsersData(createUserProfile, response);
    }

    @GetMapping
    public void getUsersInformation(final String email)
    {
        log.warn("User profile controller : getting users information");
    }
}
