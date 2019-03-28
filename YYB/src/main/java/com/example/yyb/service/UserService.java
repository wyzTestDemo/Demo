package com.example.yyb.service;

import com.example.yyb.entity.User;

import java.util.List;

public interface UserService {
    public User checkUser(User user);
    public User login(String userName, String password);
    public Integer getUploadNumById(Integer id);
    public User getUserId(Integer id);
    public Integer batUserAdd(User user);
    public Integer updateUserInfo(User user);
    public List<User> getUserByAll();
    public List<User> getUserBySoso(String userName);
    public Integer deleteUser(Integer id);
}
