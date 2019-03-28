package com.example.yyb.controller.interceptor;

import com.example.yyb.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        User user= (User)session.getAttribute("user");
        if(session!=null&&user!=null){
            return true;
        }
        /*String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            *//*告诉ajax我是重定向*//*
            response.setHeader("REDIRECT", "REDIRECT");
            *//*告诉ajax重定向的路径*//*
            response.setHeader("CONTENTPATH", basePath+"/login");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect("/login");
        }*/
        return false;
    }
}
