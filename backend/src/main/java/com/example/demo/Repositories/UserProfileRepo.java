package com.example.demo.Repositories;

import com.example.demo.Entities.UsersProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepo extends JpaRepository<UsersProfile, Long>
{
    Optional<UsersProfile> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
