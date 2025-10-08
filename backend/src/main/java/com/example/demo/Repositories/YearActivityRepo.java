package com.example.demo.Repositories;

import com.example.demo.Entities.YearActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearActivityRepo extends JpaRepository<YearActivity, Integer> {
    YearActivity findByUsersEmail(String usersEmail);
}
