package com.example.yyb.utils;

import com.example.yyb.entity.Post;
import com.example.yyb.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostingStorageUtil {
    public static Post StorageIn(String ft, String title, String path) throws Exception {
        String idName = UUID.randomUUID().toString();
        String strPath = path + "/posting/";
        File file = new File(strPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        if(title!=null&&!title.isEmpty()){
            strPath = strPath + idName  + ".txt";
        }else{
            strPath=strPath+idName+".txt";
        }
        file = new File(strPath);
        OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(file),"utf-8");
        out.append(ft);
        out.close();
        Post post = new Post();
        if(title!=null&&!title.isEmpty()){
            post.setPostName(title);
        }
        post.setPostContent(strPath);
        return post;
    }

    /*
        public static List<Post> addPostsView(Post post, List<Post> posts) {
            if (posts != null&&post!=null) {
                posts.add(post);
            }
            return posts;
        }*/
    /*设置好post的各个参数*/
    public static Post getPost(String ft_content, String title, HttpServletRequest request, Post post1,Post post,List<Post>posts,String path) {
        try {
            post = PostingStorageUtil.StorageIn(ft_content, title, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(post1!=null){
            post.setPost(post1);
        }
        post.setUser(user);
        post.setTimePost(new Date());
        return post;
    }

    /*获取帖子内容*/
    public static List<Post> getContentPosts(List<Post> posts) throws Exception {
        if (posts != null) {
            for (Post p : posts) {
                getContentPost(p);
            }
        }
        return posts;
    }

    public static void getContentPost(Post p) throws UnsupportedEncodingException {
        String urlPath = p.getPostContent();
        p.setPostContent(readTxt(urlPath));
    }

    /*读取帖子路径的内容*/
    public static String readTxt(String filePath) throws UnsupportedEncodingException {
        String line;
        String totalLine = "";
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                totalLine += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalLine;
    }


}
