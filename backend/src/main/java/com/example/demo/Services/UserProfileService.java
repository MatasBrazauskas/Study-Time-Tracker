package com.example.demo.Services;

import com.example.demo.DTOs.UserCredentials;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfile;
import com.example.demo.Repositories.UserProfileRepo;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CustomExceptions;

@Service
public class UserProfileService
{
    private final UserProfileRepo userProfileRepo;

    private final CookieUtils cookieUtils;
    private final MiddleWareUtils middleWareUtils;
    private final JwtUtils jwtUtils;

    public UserProfileService(UserProfileRepo userProfileRepo, CookieUtils cookieUtils, MiddleWareUtils middleWareUtils, JwtUtils jwtUtils)
    {
        this.userProfileRepo = userProfileRepo;
        this.cookieUtils= cookieUtils;
        this.middleWareUtils = middleWareUtils;
        this.jwtUtils = jwtUtils;
    }

    public UsersProfile findByEmail(final String email){
        return userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserNotFound());
    }

    public ResponseEntity<UserProfileOutput> getUserInformation(HttpServletRequest request){
        final var persistentCookie = middleWareUtils.extractPersistentCookie(request);

        if(persistentCookie == null) throw new CustomExceptions.UserNotFound();

        final var email = jwtUtils.extractEmail(persistentCookie.getValue());
        final var user = userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserNotFound());
        return ResponseEntity.ok(new UserProfileOutput(user));
    }

    public ResponseEntity<UserProfileOutput> registerUser(HttpServletRequest request, HttpServletResponse response, UserCredentials userCredentials)
    {
        boolean userExists = userProfileRepo.findByEmail(userCredentials.getEmail()).isEmpty();

        if(userExists) return ResponseEntity.ok(new UserProfileOutput());

        var createdUser = userProfileRepo.save(new UsersProfile(userCredentials));

        final var sessionCookie = cookieUtils.SessionCookie(createdUser.getRole());
        final var persistentCookie = cookieUtils.PersistentCookie(createdUser.getEmail());

        middleWareUtils.setSessionCookie(request, response, sessionCookie);
        middleWareUtils.setPersistentCookie(request, response, persistentCookie);

        return  ResponseEntity.ok(new UserProfileOutput(createdUser));
    }

    public ResponseEntity<UserProfileOutput> logInUser(HttpServletRequest request, HttpServletResponse response, UserCredentials userCredentials)
    {
        var user = userProfileRepo.findByEmail(userCredentials.getEmail()).orElseThrow(() -> new CustomExceptions.RegisterException());

        final var sessionCookie = cookieUtils.SessionCookie(user.getRole());
        final var persistentCookie = cookieUtils.PersistentCookie(user.getEmail());

        middleWareUtils.setSessionCookie(request, response, sessionCookie);
        middleWareUtils.setPersistentCookie(request, response, persistentCookie);

        return  ResponseEntity.ok(new UserProfileOutput(user));
    }

    public ResponseEntity<UserProfileOutput> deleteUser(HttpServletResponse response, UsersProfile userProfile){
        if(userProfile == null) throw new CustomExceptions.UserNotFound();

        userProfileRepo.delete(userProfile);
        cookieUtils.deleteCookies(response);

        return ResponseEntity.ok(new UserProfileOutput());
    }
}
