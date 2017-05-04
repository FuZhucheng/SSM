package com.fuzhu.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 符柱成 on 2017/4/28.
 */
public class CookieUtil {

    public static final int TIME = 60 * 60 * 24 * 1;  //1天存活时间

    public static void addCookie(HttpServletResponse response,
                                 String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(TIME);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response,
                                    String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static String getByName(HttpServletRequest request, String cookieName) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

}