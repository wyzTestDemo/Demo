package com.example.yyb.service;

import com.example.yyb.entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> selPostsAll();
    public Post selPostById(Integer id);
    public Integer addPost(Post post);
    public Integer dynamicPostAdd(Post post);
    /*首页热帖显示*/
    public List<Post> selPostPIndex();
    /*首页问答显示*/
    public List<Post> selPostQuestionIndex();
    public List<Post> selpostSosoResult(Post post);
}
