package com.example.demo.DTOs;

import com.example.demo.Entities.UsersProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.example.demo.Entities.UsersProfile.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserProfileOutput {
    private String username;
    private Role role;
    private LocalDate lastOnline;
    private LocalDate accCreated;

    public UserProfileOutput() {
        this.username = Role.USER.toString();
        this.role = Role.GUEST;
        this.lastOnline = LocalDate.now();
        this.accCreated = LocalDate.now();
    }

    public UserProfileOutput(UsersProfile userProfileInformation) {
        this.username = userProfileInformation.getUsername();
        this.role = userProfileInformation.getRole();
        this.lastOnline = userProfileInformation.getLastOnline();
        this.accCreated = userProfileInformation.getAccCreated();
    }
}