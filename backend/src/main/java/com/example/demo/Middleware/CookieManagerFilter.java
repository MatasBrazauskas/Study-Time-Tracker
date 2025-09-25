package com.example.demo.Middleware;

import com.example.demo.Services.UserProfileService;
import com.example.demo.Utilities.CookieUtils;
import com.example.demo.Utilities.JwtUtils;
import com.example.demo.Utilities.MiddleWareUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.Cookie;

import com.example.demo.Entities.UsersProfile.Role;

@Order(1)
@Slf4j
@Component
public class CookieManagerFilter extends OncePerRequestFilter
{
    private final MiddleWareUtils middleWareUtils;
    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final UserProfileService userProfileService;

    public CookieManagerFilter(MiddleWareUtils middleWareUtils, JwtUtils jwtUtils, CookieUtils cookieUtils, UserProfileService userProfileService) {
        this.middleWareUtils = middleWareUtils;
        this.jwtUtils = jwtUtils;
        this.cookieUtils = cookieUtils;
        this.userProfileService = userProfileService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws java.io.IOException, jakarta.servlet.ServletException
    {
        final String cookieHeader = request.getHeader("Cookie");

        log.warn("Cookie header: {}", cookieHeader);

        Cookie sessionCookie = middleWareUtils.extractSessionCookie(request);
        Cookie persistentCookie = middleWareUtils.extractPersistentCookie(request);

        if(sessionCookie == null || !jwtUtils.validateToken(sessionCookie.getValue()))
        {
            var role = Role.GUEST;

            if(persistentCookie != null)
            {
                final var email = jwtUtils.extractEmail(persistentCookie.getValue());
                final var user = userProfileService.findByEmail(email);
                role = user.getRole();

                final var newSessionCookie = cookieUtils.SessionCookie(role);
                middleWareUtils.setSessionCookie(request,response,newSessionCookie);
            }

            final var newSessionCookie = cookieUtils.SessionCookie(role);
            middleWareUtils.setSessionCookie(request, response, newSessionCookie);
        }

        sessionCookie = middleWareUtils.extractSessionCookie(request);
        persistentCookie = middleWareUtils.extractPersistentCookie(request);

        log.error("Cookies after logical checks");
        log.info("Session JWT: {}", sessionCookie == null ? "null" : sessionCookie.getValue());
        log.info("Persistent JWT: {}", persistentCookie == null ? "null" : persistentCookie.getValue());

        filterChain.doFilter(request, response);
    }
}
