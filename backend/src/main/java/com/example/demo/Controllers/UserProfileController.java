package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Services.UserProfileService;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<UserProfileOutput> logTheUser(@RequestBody CreateUserProfile  createUserProfile, HttpServletResponse response)
    {
        log.info("UserProfileController: logTheUser");
        return userProfileService.createUsersInDataBase(createUserProfile, response);
    }

    @GetMapping
    public ResponseEntity<UserProfileOutput> getUsersInformation(final String email)
    {
        log.warn("createCookies");
        var user = userProfileService.findByEmail(email);
        return ResponseEntity.ok(new UserProfileOutput(user));
    }
}
