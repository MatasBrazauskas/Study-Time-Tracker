package com.example.demo.Services;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfileInformation;
import com.example.demo.Repositories.UserProfileRepo;
import com.example.demo.Utilities.CookieUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.example.demo.Exceptions.CustomExceptions;

@Service
public class UserProfileService
{
    private final UserProfileRepo userProfileRepo;

    //temp
    private final CookieUtils cookieUtils;

    public UserProfileService(UserProfileRepo userProfileRepo, CookieUtils cookieUtils)
    {
        this.userProfileRepo = userProfileRepo;
        this.cookieUtils= cookieUtils;
    }

    public ResponseEntity<UserProfileOutput> registerInDb(CreateUserProfile createUserProfile, HttpServletResponse response)
    {
        boolean userDoNotExists = userProfileRepo.findByEmail(createUserProfile.getEmail()).isEmpty();

        if(userDoNotExists){
            var createdUser = userProfileRepo.save(new  UsersProfileInformation(createUserProfile));

            cookieUtils.addSessionCookie(response, createdUser.getRole());
            cookieUtils.addPersistentCookie(response, createdUser.getEmail());

            return  ResponseEntity.ok(new UserProfileOutput(createdUser));
        }
        throw new CustomExceptions.LogInException();
    }

    public ResponseEntity<UserProfileOutput> retrieveUsersData(CreateUserProfile createUserProfile, HttpServletResponse response)
    {
        var user = userProfileRepo.findByEmail(createUserProfile.getEmail());

        if(user.isEmpty() == false){
            cookieUtils.addSessionCookie(response, user.get().getRole());
            cookieUtils.addPersistentCookie(response, createUserProfile.getEmail());

            return  ResponseEntity.ok(new UserProfileOutput(user.get()));
        }
        throw new CustomExceptions.LogInException();
    }

    public UsersProfileInformation findByEmail(final String email){
        return userProfileRepo.findByEmail(email).get();
    }
}
