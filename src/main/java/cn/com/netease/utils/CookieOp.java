package cn.com.netease.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.netease.models.User;

/**
 *
 * 添加Cookie
 * 验证Cookie
 */
public class CookieOp {

    //为合法用户创建cookie
    public void setCookie(User user, HttpServletResponse response){
        int userType = user.getUsertype();
        Cookie userNameCookie = new Cookie("userName", user.getUsername()) ;
        userNameCookie.setMaxAge(24 * 60 * 60);
//        userTypeCookie.setMaxAge(24 * 60 * 60);
        System.out.println(userNameCookie.getValue());
//        System.out.println(userTypeCookie.getValue());
        response.addCookie(userNameCookie);
//        response.addCookie(userTypeCookie);
    }

    //提取Cookie中的数据
    public User getCooke(HttpServletRequest request){
        User user = new User();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getValue());
                if (cookie.getName().equals("userName")) {
                    user.setUsername(cookie.getValue());
                }
                if (cookie.getName().equals("userType")) {
                    if(cookie.getValue().equals(1)){
                        user.setUsertype(1);
                    }else{
                        user.setUsertype(0);
                    }
                }
            }
        }
        return user;
    }
}
