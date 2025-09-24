package com.example.demo.Utilities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class MiddleWareUtils
{
    public static final String SessionsCookieName = "sessionCookie";
    public static final String PersistentCookieName = "persistentCookie";

    public String extractSessionCookie(final HttpServletRequest request)
    {
        if(request.getCookies() == null) {
            log.info("Session cookies are empty");
            return null;
        }

        for(Cookie cookie : request.getCookies())
        {
            log.warn(cookie.getName());
            if(cookie.getName().equals(SessionsCookieName))
                return cookie.getValue();
        }
        return null;
    }
    public String extractPersistentCookie(final HttpServletRequest request) {
        if(request.getCookies() == null) {
            log.info("Persistent cookies are empty");
            return null;
        }

        for(Cookie cookie : request.getCookies())
        {
            if(cookie.getName().equals(PersistentCookieName))
                return cookie.getValue();
        }
        return null;
    }

    public void setSessionJwt(HttpServletRequest request, HttpServletResponse response, final String sessionJwt)
    {
        request.setAttribute(SessionsCookieName, sessionJwt);
        if(sessionJwt != null)
            response.addCookie(new Cookie(SessionsCookieName, sessionJwt));
    }
     public void setPersistentJwt(HttpServletRequest request, HttpServletResponse response, final String persistentJwt)
     {
         request.setAttribute(PersistentCookieName, persistentJwt);
         if(persistentJwt != null)
            response.addCookie(new Cookie(PersistentCookieName, persistentJwt));
     }
}
