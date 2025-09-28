package com.example.demo.DTOs;

import com.example.demo.Entities.UsersProfile.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUserProfile
{
    @Builder.Default
    private String username = Role.GUEST.toString();
    @Builder.Default
    private String email = "";
    @Builder.Default
    private Role role = Role.USER;
}
