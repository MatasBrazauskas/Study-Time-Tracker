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

    public Cookie extractSessionCookie(final HttpServletRequest request)
    {
        if(request.getCookies() == null) return null;

        for(Cookie cookie : request.getCookies())
        {
            log.warn(cookie.getName());
            if(cookie.getName().equals(SessionsCookieName))
                return cookie;
        }
        return null;
    }
    public Cookie extractPersistentCookie(final HttpServletRequest request) {
        if(request.getCookies() == null) return null;

        for(Cookie cookie : request.getCookies())
        {
            if(cookie.getName().equals(PersistentCookieName))
                return cookie;
        }
        return null;
    }

    public void setSessionCookie(HttpServletRequest request, HttpServletResponse response, final Cookie sessionCookie)
    {
        if(sessionCookie != null){
            request.setAttribute(SessionsCookieName, sessionCookie);
            response.addCookie(sessionCookie);
        }
    }
     public void setPersistentCookie(HttpServletRequest request, HttpServletResponse response, final Cookie persistentCookie)
     {
         if (persistentCookie != null) {
             request.setAttribute(PersistentCookieName, persistentCookie.getValue());
             response.addCookie(persistentCookie);
         }
     }
}
