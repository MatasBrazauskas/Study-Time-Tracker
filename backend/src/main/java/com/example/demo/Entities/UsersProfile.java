package com.example.demo.Entities;

import com.example.demo.DTOs.UserCredentials;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "UserProfile", indexes = {@Index(columnList = "email")})
@Data
public class UsersProfile
{
    private static final int MAX_USERNAME_LENGTH = 255;
    public enum Role{
        USER,
        GUEST,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_USERNAME_LENGTH)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('USER', 'GUEST')")
    private Role role;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "DATE DEFAULT(CURRENT_DATE)")
    private LocalDate accCreated;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "DATE DEFAULT(CURRENT_DATE)")
    private LocalDate lastOnline;

    public UsersProfile(){
        this.username = this.email = "";
        this.role = Role.GUEST;
        this.accCreated = LocalDate.now();
        this.lastOnline = LocalDate.now();
    }

    public UsersProfile(UserCredentials newUser)
    {
        this.username = newUser.getUsername();
        this.email = newUser.getEmail();
        this.role = Role.USER;
    }
}
