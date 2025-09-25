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

    /*public ResponseEntity<Void> deletePersistentCookie(HttpServletResponse response){
        //this.SessionCookie(response, Role.GUEST);

        var deleteCookie = new Cookie(PersistentCookieName, "");

        deleteCookie.setPath("/");
        deleteCookie.setHttpOnly(true);
        deleteCookie.setDomain("localhost");
        deleteCookie.setMaxAge(0);

        response.addCookie(deleteCookie);

        return ResponseEntity.ok().build();
    }*/
}