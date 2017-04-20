package cn.com.netease.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.netease.models.User;

/**
 * 
 * 添加Session
 * 验证Session
 */
public class SesssionOP {
    private HttpSession session;
    //制作Session
    public void setSession(User user, HttpServletRequest request){
        session = request.getSession();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("usertype", user.getUsertype());
        session.setMaxInactiveInterval(24 * 60 * 60);
    }

    //得到Session
    public User getSession(HttpServletRequest request){
        User user = new User();
        session = request.getSession();
        String username = (String) session.getAttribute("username");
        Integer usertype = (Integer) session.getAttribute("usertype");
        if (username != null && usertype != null) {
            user.setUsername(username);
            user.setUsertype(usertype);
            return user;
        }else
            return null;
    }

    //销毁session
    public void destroySession(HttpServletRequest request){
        session = request.getSession();
        session.invalidate();
    }
}
