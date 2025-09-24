package com.example.demo.Middleware;

import com.example.demo.Entities.UsersProfileInformation;
import com.example.demo.Services.UserProfileService;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Entities.UsersProfileInformation.Role;

import java.util.List;

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

        var persistentJwt = middleWareUtils.extractPersistentCookie(request);
        var user = new UsersProfileInformation();

        /*if(persistentJwt == null){
            var roleAuthority = new SimpleGrantedAuthority(Role.GUEST.toString());
            var authToken = new UsernamePasswordAuthenticationToken(null, null, List.of(roleAuthority));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }else {
            final var email = jwtUtils.extractEmail(persistentJwt);
            final var user = userProfileService.findByEmail(email);

            var roleAuthority = new SimpleGrantedAuthority(user.getRole().toString());
            var authToken = new UsernamePasswordAuthenticationToken(null, null, List.of(roleAuthority));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }*/

        if(persistentJwt != null) {
            final var email = jwtUtils.extractEmail(persistentJwt);
            user =  userProfileService.findByEmail(email);
        }

        var roleAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        var authToken = new UsernamePasswordAuthenticationToken(user, null, List.of(roleAuthority));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
