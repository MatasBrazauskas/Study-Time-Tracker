package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "UserProfile")
@Data
public class UsersProfileInformation
{
    public static enum Role{
        GUEST,
        USER,
        ADMIN,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private Role role;
    private LocalDate accCreated;
    private LocalDate lastOnline;
}
