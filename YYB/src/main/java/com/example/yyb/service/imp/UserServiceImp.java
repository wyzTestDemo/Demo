package com.example.yyb.service.imp;

import com.example.yyb.entity.User;
import com.example.yyb.mapper.UserMapper;
import com.example.yyb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.dynaemicUserSel(user);
    }
    @Override
    public User login(String userName, String password) {
        User user = null;
        user = userMapper.getUser(userName, password);
        return user;
    }
    @Override
    public Integer getUploadNumById(Integer id) {
        Integer uploadNum = userMapper.getUploadNum(id);
        return uploadNum;
    }
    @Override
    public User getUserId(Integer id) {
        User user = userMapper.getUserPostById(id);
        return user;
    }

    @Override
    public Integer batUserAdd(User user) {
        return userMapper.batUserAdd(user);
    }
    @Override
    public Integer updateUserInfo(User user) {
        return userMapper.dynamicUserUp(user);
    }

    @Override
    public List<User> getUserByAll() {
        return userMapper.getUserByAll();
    }

    @Override
    public List<User> getUserBySoso(String userName) {
        return userMapper.getUserBySoso(userName);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

}
