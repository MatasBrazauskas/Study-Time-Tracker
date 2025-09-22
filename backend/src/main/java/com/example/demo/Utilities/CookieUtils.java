package com.example.demo.Utilities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.Entities.UsersProfileInformation.Role;
import static com.example.demo.Utilities.MiddleWareUtils.SessionsCookieName;
import static com.example.demo.Utilities.MiddleWareUtils.PersistentCookieName;

@Slf4j
@Component
@Data
public class CookieUtils
{
    private final int PERSISTENT_COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    public JwtUtils jwtUtils;

    public CookieUtils(JwtUtils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    public String addPersistentCookie(HttpServletResponse response, String email)
    {
        final var jwtToken = jwtUtils.generatePersistentJWT(email);
        var persistentCookie = new Cookie(PersistentCookieName, jwtToken);

        persistentCookie.setMaxAge(PERSISTENT_COOKIE_MAX_AGE);
        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setAttribute("SameSite", "Lax");

        response.addCookie(persistentCookie);

        return jwtToken;
    }

    public String addSessionCookie(HttpServletResponse response, Role role)
    {
        final var jwtToken = jwtUtils.generateSessionJWT(role.toString());
        var sessionCookie = new Cookie(SessionsCookieName, jwtToken);

        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setDomain("localhost");
        sessionCookie.setAttribute("SameSite", "Lax");

        response.addCookie(sessionCookie);

        return jwtToken;
    }

    public ResponseEntity<Void> deletePersistentCookie(HttpServletResponse response){
        this.addSessionCookie(response, Role.GUEST);

        var deleteCookie = new Cookie(PersistentCookieName, "");

        deleteCookie.setPath("/");
        deleteCookie.setHttpOnly(true);
        deleteCookie.setDomain("localhost");
        deleteCookie.setMaxAge(0);

        response.addCookie(deleteCookie);

        return ResponseEntity.ok().build();
    }
}