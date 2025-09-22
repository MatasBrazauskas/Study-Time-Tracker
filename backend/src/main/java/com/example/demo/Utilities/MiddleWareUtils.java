package com.example.demo.Utilities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public final class MiddleWareUtils
{
    public static final String SessionsCookieName = "sessionCookie";
    public static final String PersistentCookieName = "persistentCookie";

    public String extractSessionCookie(HttpServletRequest request)
    {
        if(request.getSession(false) == null)
            return null;

        for(Cookie cookie : request.getCookies())
        {
            if(cookie.getName().equals(SessionsCookieName))
                return cookie.getValue();
        }
        return null;
    }
    public String extractPersistentCookie(HttpServletRequest request)
    {
        if(request.getSession(false) == null)
            return null;

        for(Cookie cookie : request.getCookies())
        {
            if(cookie.getName().equals(PersistentCookieName))
                return cookie.getValue();
        }
        return null;
    }

    public void setSessionJwt(HttpServletRequest request, final String sessionJwt)
    {
        request.setAttribute(SessionsCookieName, sessionJwt);
    }
     public void setPersistentJwt(HttpServletRequest request, final String persistentJwt)
     {
         request.setAttribute(PersistentCookieName, persistentJwt);
     }
}
