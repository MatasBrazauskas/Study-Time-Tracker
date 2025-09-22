package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserProfileOutput {
    private String name;
    private String role;
    private LocalDate lastOnline;
    private LocalDate accCreated;

    public UserProfileOutput(final String name, final String role) {
        this.name = name;
        this.role = role;
        this.lastOnline = LocalDate.now();
        this.accCreated = LocalDate.now();
    }
}
