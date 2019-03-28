package com.example.yyb.controller;

import com.example.yyb.entity.Post;
import com.example.yyb.service.PostService;
import com.example.yyb.utils.PostingStorageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class InformationCtr {
    @Value("${path}")
    private String path;
    @Autowired
    private PostService postService;
    @RequestMapping("/information")
    public ModelAndView information(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        ModelAndView information = new ModelAndView("information/information");
        PageHelper.startPage(pn,10);
        List<Post> posts = postService.selPostsAll();
        try {
            posts = PostingStorageUtil.getContentPosts(posts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo<>(posts,10);
        System.out.println(pageInfo);
        request.setAttribute("pageInfo", pageInfo);
        /*表情包*/
        return information;
    }
    @RequestMapping("/specificComments")
    public ModelAndView specificComments(@RequestParam("spencificCommentsId") Integer id,@RequestParam(value = "pn",defaultValue = "1") Integer pn,HttpServletRequest request){
        ModelAndView result = new ModelAndView("information/specificComments");
        Post post = postService.selPostById(id);
        try {
            PostingStorageUtil.getContentPost(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pn,10);
        List<Post> list = post.getPosts();
        try {
            PostingStorageUtil.getContentPosts(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo<>(list,10);
        result.addObject("post", post);
        request.setAttribute("pageInfo", pageInfo);
        return result;
    }
    @RequestMapping(value = "/myPosting",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap posting(@RequestParam("ft_content") String ft_content, @RequestParam("title") String title, @RequestParam("postId") Integer postId,HttpServletRequest request){
        ModelMap result = new ModelMap();
        Post post = null;
        /*回复的帖子*/
        Post post1 = null;
        List<Post> posts = null;
        if(postId!=null){
            post1 =  postService.selPostById(postId);
            posts = postService.selPostsAll();
        }
        post = PostingStorageUtil.getPost(ft_content, title, request, post1,post,posts,path);
        postService.addPost(post);
        post.getId();
        return result;
    }


}
