package com.example.demo.Services;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.DTOs.UserProfileOutput;
import com.example.demo.Entities.UsersProfileInformation;
import com.example.demo.Repositories.UserProfileRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService
{
    private final UserProfileRepo userProfileRepo;

    public UserProfileService(UserProfileRepo userProfileRepo)
    {
        this.userProfileRepo = userProfileRepo;
    }

    public ResponseEntity<UserProfileOutput> createUsersInDataBase(CreateUserProfile createUserProfile, HttpServletResponse response)
    {
        var existingUser = userProfileRepo.findByEmail(createUserProfile.getEmail());

        if(existingUser.isEmpty())
        {
            var newProfile = new UsersProfileInformation();

            newProfile.setUsername(createUserProfile.getName());
            newProfile.setEmail(createUserProfile.getEmail());
            newProfile.setRole(com.example.demo.Entities.UsersProfileInformation.Role.USER);

            var createdUser = userProfileRepo.save(newProfile);
            return ResponseEntity.ok(new UserProfileOutput(createdUser));
        }

        return ResponseEntity.ok(new UserProfileOutput(existingUser.get()));
    }

    public UsersProfileInformation findByEmail(final String email){
        return userProfileRepo.findByEmail(email).get();
    }
}
