package com.example.demo.Repositories;

import com.example.demo.DTOs.CreateUserProfile;
import com.example.demo.Entities.UsersProfileInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepo extends JpaRepository<UsersProfileInformation, Long>
{
    Optional<UsersProfileInformation> findByEmail(String email);

    Optional<UsersProfileInformation> save(CreateUserProfile profile);
}
