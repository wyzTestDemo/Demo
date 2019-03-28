package com.example.yyb.controller.filter;

import com.example.yyb.entity.Post;
import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.service.MyUploadService;
import com.example.yyb.service.PostService;
import com.example.yyb.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
@WebFilter(filterName = "indexFilter",urlPatterns = {"/index"})
public class IndexFilter implements Filter {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private MyUploadService myUploadService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        List<Post> postsP = postService.selPostPIndex();
        List<Post> postsQ = postService.selPostQuestionIndex();
        request.setAttribute("postsP", postsP);
        request.setAttribute("postsQ", postsQ);
        //*社区音乐*//*
        UploadMusic uploadMusic = new UploadMusic();
        uploadMusic.setThrough(true);
        HttpSession session = request.getSession(false);
        User user = null;
        if(session!=null){
            user = (User)session.getAttribute("user");
        }
        PageHelper.startPage(1, 5);
        List<UploadMusic> uploadAll = myUploadService.getUploadAll(uploadMusic,user);
        PageInfo pageInfo = new PageInfo<>(uploadAll,5);
        request.setAttribute("musicIndex", pageInfo.getList());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
