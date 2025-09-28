package com.example.demo.Middleware;

import com.example.demo.DTOs.AuthUserProfile;
import com.example.demo.DTOs.UserCredentials;
import com.example.demo.Entities.UsersProfile;
import com.example.demo.Services.UserProfileService;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Slf4j
@Order(2)
@Component
public class AuthorizationFilter extends OncePerRequestFilter
{
    private final MiddleWareUtils middleWareUtils;
    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final UserProfileService userProfileService;

    public AuthorizationFilter(MiddleWareUtils middleWareUtils, JwtUtils jwtUtils, CookieUtils cookieUtils, UserProfileService userProfileService) {
        this.middleWareUtils = middleWareUtils;
        this.jwtUtils = jwtUtils;
        this.cookieUtils = cookieUtils;
        this.userProfileService = userProfileService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws java.io.IOException, jakarta.servlet.ServletException {

        final var persistentCookie = middleWareUtils.extractPersistentCookie(request);
        AuthUserProfile user = new AuthUserProfile();

        if(persistentCookie != null) {
            final var email = jwtUtils.extractEmail(persistentCookie.getValue());
            final var dbUser =  userProfileService.findByEmail(email);
            user = new AuthUserProfile(dbUser.getUsername(), dbUser.getEmail(), dbUser.getRole());
        }

        var roleAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        var authToken = new UsernamePasswordAuthenticationToken(user, null, List.of(roleAuthority));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.warn("Users role: {}", user.getRole().toString());

        filterChain.doFilter(request, response);
    }
}
