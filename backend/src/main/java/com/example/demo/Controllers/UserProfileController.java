package com.example.demo.Controllers;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfileInformation;
import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Services.UserProfileService;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/userProfile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    //TODO temp
    private final JwtUtils jwtUtils;
    private final MiddleWareUtils middleWareUtils;

    public UserProfileController(UserProfileService userProfileService,  JwtUtils jwtUtils, MiddleWareUtils middleWareUtils) {
        this.userProfileService = userProfileService;
        this.jwtUtils = jwtUtils;
        this.middleWareUtils = middleWareUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileOutput> RegisterUser(HttpServletRequest request, HttpServletResponse response, @RequestBody CreateUserProfile  createUserProfile)
    {
        log.info("UserProfileController: register user");
        return userProfileService.registerInDb(request, response, createUserProfile);
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserProfileOutput> LogInTheUser(HttpServletRequest request, HttpServletResponse response, @RequestBody CreateUserProfile  createUserProfile)
    {
        log.info("User profile controller: log in");
        return userProfileService.retrieveUsersData(request, response, createUserProfile);
    }

    @GetMapping
    public ResponseEntity<?> getUsersInformation(HttpServletRequest request)
    {
        log.warn("User profile controller : getting users information");
        final var temp = middleWareUtils.extractPersistentCookie(request);

        if(temp == null){
            return ResponseEntity.ok().build();
        }

        final var email = jwtUtils.extractEmail(temp.getValue());
        return ResponseEntity.ok(userProfileService.findByEmail(email));
    }

    @DeleteMapping
    public void deleteUser()
    {
        log.warn("User is USER");
    }
}
