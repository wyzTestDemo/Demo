package com.example.yyb.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Post  {
    private Integer id;
    private String postName;
    private String postContent;
    private Date timePost;
    private User user;
    private List<Post> posts;
    private Post post;
    private UploadMusic uploadMusic;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", postContent='" + postContent + '\'' +
                ", timePost=" + timePost +
                ", user=" + user +
                ", post=" + post +
                ", uploadMusic=" + uploadMusic +
                '}';
    }

    public UploadMusic getUploadMusic() {
        return uploadMusic;
    }

    public void setUploadMusic(UploadMusic uploadMusic) {
        this.uploadMusic = uploadMusic;
    }


    public Post() {
    }
    public Post(String postName, String postContent, Date timePost) {
        this.postName = postName;
        this.postContent = postContent;
        this.timePost = timePost;
    }

    public Post(String postName, String postContent, Date timePost, User user) {
        this.postName = postName;
        this.postContent = postContent;
        this.timePost = timePost;
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getTimePost() {
        String newDate = "";
        if(timePost!=null){
            SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
            newDate = format.format(timePost);
        }
        return newDate;
    }

    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
