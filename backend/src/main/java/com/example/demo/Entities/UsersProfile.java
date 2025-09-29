package com.example.demo.Entities;

import com.example.demo.DTOs.UserCredentials;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserProfile", indexes = {@Index(columnList = "email")})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Builder.Default
    private String username = Role.GUEST.toString();

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('USER', 'GUEST')")
    @Builder.Default
    private Role role = Role.USER;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "DATE DEFAULT(CURRENT_DATE)")
    private LocalDate accCreated;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "DATE DEFAULT(CURRENT_DATE)")
    private LocalDate lastOnline;

    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<YearActivity> activityList = new ArrayList<>();*/

    public UsersProfile(UserCredentials newUser)
    {
        this.username = newUser.getUsername();
        this.email = newUser.getEmail();
        this.role = Role.USER;
    }
}
