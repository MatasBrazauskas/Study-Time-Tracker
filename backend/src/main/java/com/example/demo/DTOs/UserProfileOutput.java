package com.example.demo.DTOs;

import com.example.demo.Entities.UsersProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

import com.example.demo.Entities.UsersProfile.Role;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileOutput {

    @Builder.Default
    private String username = Role.GUEST.toString();

    @Builder.Default
    private Role role = Role.GUEST;

    private LocalDate lastOnline;
    private LocalDate accCreated;

    public UserProfileOutput(UsersProfile userProfileInformation) {
        this.username = userProfileInformation.getUsername();
        this.role = userProfileInformation.getRole();
        this.lastOnline = userProfileInformation.getLastOnline();
        this.accCreated = userProfileInformation.getAccCreated();
    }
}