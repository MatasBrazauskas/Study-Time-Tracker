package com.example.demo.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "UserProfile")
@Data
public class UsersProfileInformation
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
}
