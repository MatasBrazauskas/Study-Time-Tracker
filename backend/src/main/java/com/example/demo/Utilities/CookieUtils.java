package com.example.demo.Utilities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.example.demo.Entities.UsersProfile.Role;
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

    public Cookie PersistentCookie(String email)
    {
        final var jwtToken = jwtUtils.generatePersistentJWT(email);
        var cookie = new Cookie(PersistentCookieName, jwtToken);

        cookie.setMaxAge(PERSISTENT_COOKIE_MAX_AGE);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "Lax");

        return cookie;
    }

    public Cookie SessionCookie(Role role)
    {
        final var jwtToken = jwtUtils.generateSessionJWT(role.toString());
        var cookie = new Cookie(SessionsCookieName, jwtToken);

        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "Lax");

        return cookie;
    }

    public void deleteCookies(HttpServletResponse response){
        final var sessionCookie = this.SessionCookie(Role.GUEST);

        var persistentCookie = new Cookie(PersistentCookieName, "");

        persistentCookie.setPath("/");
        persistentCookie.setHttpOnly(true);
        persistentCookie.setDomain("localhost");
        persistentCookie.setMaxAge(0);

        response.addCookie(sessionCookie);
        response.addCookie(persistentCookie);
    }
}