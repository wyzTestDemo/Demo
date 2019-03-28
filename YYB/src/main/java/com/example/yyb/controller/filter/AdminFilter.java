package com.example.yyb.controller.filter;

import com.example.yyb.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter(filterName = "adminFilter",urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User) session.getAttribute("user");
            /*设置一个角色配置管理员权限*/
            if(user!=null){
                if("admin".equals(user.getRole())){
                    filterChain.doFilter(request, response);
                }else {
                    response.sendRedirect("/index");
                }
            }else {
                response.sendRedirect("/login");
            }
        }else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
