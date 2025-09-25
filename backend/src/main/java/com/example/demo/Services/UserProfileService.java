package com.example.demo.Services;

import com.example.demo.DTOs.UserCredentials;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfile;
import com.example.demo.Repositories.UserProfileRepo;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CustomExceptions;

@Service
public class UserProfileService
{
    private final UserProfileRepo userProfileRepo;

    //temp
    private final CookieUtils cookieUtils;
    private final MiddleWareUtils middleWareUtils;

    public UserProfileService(UserProfileRepo userProfileRepo, CookieUtils cookieUtils, MiddleWareUtils middleWareUtils)
    {
        this.userProfileRepo = userProfileRepo;
        this.cookieUtils= cookieUtils;
        this.middleWareUtils = middleWareUtils;
    }

    public ResponseEntity<UserProfileOutput> registerInDb(HttpServletRequest request, HttpServletResponse response, UserCredentials userCredentials)
    {
        boolean userDoNotExists = userProfileRepo.findByEmail(userCredentials.getEmail()).isEmpty();

        if(userDoNotExists){
            var createdUser = userProfileRepo.save(new UsersProfile(userCredentials));

            final var sessionCookie = cookieUtils.SessionCookie(createdUser.getRole());
            final var persistentCookie = cookieUtils.PersistentCookie(createdUser.getEmail());

            middleWareUtils.setSessionCookie(request, response, sessionCookie);
            middleWareUtils.setPersistentCookie(request, response, persistentCookie);

            return  ResponseEntity.ok(new UserProfileOutput(createdUser));
        }
        throw new CustomExceptions.LogInException();
    }

    public ResponseEntity<UserProfileOutput> retrieveUsersData(HttpServletRequest request, HttpServletResponse response, UserCredentials userCredentials)
    {
        var user = userProfileRepo.findByEmail(userCredentials.getEmail());

        if(user.isEmpty() == false){
            final var sessionCookie = cookieUtils.SessionCookie(user.get().getRole());
            final var persistentCookie = cookieUtils.PersistentCookie(user.get().getEmail());

            middleWareUtils.setSessionCookie(request, response, sessionCookie);
            middleWareUtils.setPersistentCookie(request, response, persistentCookie);

            return  ResponseEntity.ok(new UserProfileOutput(user.get()));
        }
        throw new CustomExceptions.LogInException();
    }

    public UsersProfile findByEmail(final String email){
        return userProfileRepo.findByEmail(email).orElseThrow(() -> new CustomExceptions.UserNotFound());
    }

    public ResponseEntity<UserProfileOutput> deleteUser(HttpServletResponse response, UsersProfile userProfile){
        if(userProfile == null){
            throw new CustomExceptions.UserNotFound();
        }

        userProfileRepo.delete(userProfile);
        cookieUtils.deleteCookies(response);

        return ResponseEntity.ok(new UserProfileOutput());
    }
}
