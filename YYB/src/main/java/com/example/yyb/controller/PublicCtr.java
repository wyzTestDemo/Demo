package com.example.yyb.controller;

import com.example.yyb.entity.Post;
import com.example.yyb.service.PostService;
import com.example.yyb.utils.PostingStorageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PublicCtr {
    @Autowired
    private PostService postService ;
    @RequestMapping("/search")
    public ModelAndView mySearch(Post post,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        post.setPostName(post.getPostName());
        ModelAndView result = new ModelAndView("information/information");
        PageHelper.startPage(pn,10);
        List<Post> posts = postService.selpostSosoResult(post);
        try {
            posts = PostingStorageUtil.getContentPosts(posts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo<>(posts,10);
        result.addObject("pageInfo", pageInfo);
        return  result;
    }
}
