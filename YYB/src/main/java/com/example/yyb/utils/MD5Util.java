package com.example.yyb.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    public static void main(String[] args) {
        String str = DigestUtils.md5DigestAsHex("wyz483".getBytes());
        System.out.println(str);
    }
    public static  String getToken(String key){
        String token = "";
        token = DigestUtils.md5DigestAsHex(key.getBytes()).substring(3, 12)+key.substring(1,7);
        return token;
    }
}
