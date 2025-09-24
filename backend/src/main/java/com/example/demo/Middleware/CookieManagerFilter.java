package com.example.demo.Middleware;

import com.example.demo.Entities.UsersProfileInformation;
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

import com.example.demo.Entities.UsersProfileInformation.Role;

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
        String sessionJwt = middleWareUtils.extractSessionCookie(request);
        String persistentJwt = middleWareUtils.extractPersistentCookie(request);

        middleWareUtils.setSessionJwt(request, response,sessionJwt);
        middleWareUtils.setPersistentJwt(request, response, persistentJwt);

        log.error("");

        if(sessionJwt == null || !jwtUtils.validateToken(sessionJwt))
        {
            var role = Role.GUEST;

            if(persistentJwt != null)
            {
                final var email = jwtUtils.extractEmail(persistentJwt);
                final var user = userProfileService.findByEmail(email);
                role = user.getRole();
            }

            final var newSessionJwt = cookieUtils.addSessionCookie(response, role);
            middleWareUtils.setSessionJwt(request, response,newSessionJwt);
        }

        sessionJwt = middleWareUtils.extractSessionCookie(request);
        persistentJwt = middleWareUtils.extractPersistentCookie(request);

        log.info("Session JWT: {}", sessionJwt);
        log.info("Persistent JWT: {}", persistentJwt);

        filterChain.doFilter(request, response);
    }
}
