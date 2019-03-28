package com.example.yyb.service.imp;

import com.example.yyb.entity.Post;
import com.example.yyb.mapper.PostMapper;
import com.example.yyb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Override
    public List<Post> selPostsAll() {
        List<Post> postAll = postMapper.getPostAll();
        return postAll;
    }
    @Override
    public Post selPostById(Integer id) {
        return postMapper.getPostById(id);
    }
    @Override
    public Integer addPost(Post post) {
        Integer result = postMapper.addPost(post);
        return result;
    }
    @Override
    public Integer dynamicPostAdd(Post post) {

        return postMapper.dynamicPostAdd(post);
    }
    @Override
    public List<Post> selPostPIndex() {
        return postMapper.getPostPIndex();
    }
    @Override
    public List<Post> selPostQuestionIndex() {
        return postMapper.getPostQuestionIndex();
    }
    @Override
    public List<Post> selpostSosoResult(Post post) {
        return postMapper.getPostSosoResult(post);
    }
}
