package com.example.demo.Services;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.Repositories.UserProfileRepo;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService
{
    private final UserProfileRepo userProfileRepo;

    public UserProfileService(UserProfileRepo userProfileRepo)
    {
        this.userProfileRepo = userProfileRepo;
    }

    public ResponseEntity<?> createUsersInDataBase(CreateUserProfile createUserProfile)
    {
        var user = userProfileRepo.findByEmail(createUserProfile.getEmail());

        if(user.isEmpty())
        {

        }else{

        }

        return ResponseEntity.ok().build();
    }
}
