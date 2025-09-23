package com.example.demo.DTOs;

import com.example.demo.Entities.UsersProfileInformation;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.example.demo.Entities.UsersProfileInformation.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserProfileOutput {
    private String username;
    private Role role;
    private LocalDate lastOnline;
    private LocalDate accCreated;

    public UserProfileOutput(UsersProfileInformation userProfileInformation) {
        this.username = userProfileInformation.getUsername();
        this.role = userProfileInformation.getRole();
        this.lastOnline = userProfileInformation.getLastOnline();
        this.accCreated = userProfileInformation.getAccCreated();
    }
}