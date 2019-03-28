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
@WebFilter(filterName = "userFilter",urlPatterns = {"/myPosting","/myupload/*","/myUser/*","/upload","/cancelCollection","/collectionMusic"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User) session.getAttribute("user");
            if(user!=null){
                filterChain.doFilter(request, response);
            }else {
                String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                    /*告诉ajax我是重定向*/
                    response.setHeader("REDIRECT", "REDIRECT");
                    /*告诉ajax重定向的路径*/
                    response.setHeader("CONTENTPATH", basePath+"/login");
                    System.out.println("basePath = " + basePath);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }else{
                    response.sendRedirect("/login");
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
