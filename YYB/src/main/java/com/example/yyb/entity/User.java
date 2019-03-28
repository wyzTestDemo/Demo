package com.example.yyb.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class User  {
    private int id;
    private String userName;
    private String password;
    private String phone;
    private String image;
    private String sex;
    private Date birth;
    private String introduction;
    private Date registerTime;
    private List<UploadMusic> uploadMusics;
    private List<Post> posts;
    private String role;
    private List<CollectionItem> collectionItems;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", introduction='" + introduction + '\'' +
                ", registerTime=" + registerTime +
                ", role='" + role + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CollectionItem> getCollectionItems() {
        return collectionItems;
    }

    public void setCollectionItems(List<CollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
    }

    public String getRegisterTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        String formatDate = null;
        if(registerTime!=null){
            formatDate = format.format(registerTime);
        }
        return formatDate;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBirth() {
        String formatDate = null;
        if (birth != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            formatDate = format.format(birth);
        }
        return formatDate;
    }

    public void setBirth(String birth) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newBirth = null;
        try {
            newBirth = format.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.birth = newBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UploadMusic> getUploadMusics() {
        return uploadMusics;
    }

    public void setUploadMusics(List<UploadMusic> uploadMusics) {
        this.uploadMusics = uploadMusics;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
