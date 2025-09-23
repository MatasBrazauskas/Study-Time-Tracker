package com.example.demo.Middleware;

import com.example.demo.Entities.UsersProfileInformation;
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

    public CookieManagerFilter(MiddleWareUtils middleWareUtils, JwtUtils jwtUtils, CookieUtils cookieUtils) {
        this.middleWareUtils = middleWareUtils;
        this.jwtUtils = jwtUtils;
        this.cookieUtils = cookieUtils;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws java.io.IOException, jakarta.servlet.ServletException
    {
        final String sessionJwt = middleWareUtils.extractSessionCookie(request);
        final String persistentJwt = middleWareUtils.extractPersistentCookie(request);

        middleWareUtils.setSessionJwt(request,sessionJwt);
        //middleWareUtils.setPersistentJwt(request,persistentJwt);

        log.info("Session JWT: {}", sessionJwt);
        log.info("Persistent JWT: {}", persistentJwt);

        if(sessionJwt == null || !jwtUtils.validateToken(sessionJwt))
        {
            var role = Role.GUEST;

            /*if(persistentJwt != null)
            {

            }*/
            final var newSessionJwt = cookieUtils.addSessionCookie(response, role);
            middleWareUtils.setSessionJwt(request,newSessionJwt);
        }

        filterChain.doFilter(request, response);
    }
}
