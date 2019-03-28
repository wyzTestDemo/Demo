package com.example.yyb.utils;

import com.example.yyb.entity.User;

public class UserUtil {
    public static boolean validationUser(User user){
        boolean result = false;
        String regUserName = "^1[34578]\\d{9}$";
        String regPassword = "^[a-z0-9_-]{6,15}$";
        if(user.getUserName().matches(regUserName)&&user.getPassword().matches(regPassword)){
            result = true;
        }
        return result;
    }
}
