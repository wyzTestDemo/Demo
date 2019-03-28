package com.example.yyb.entity;

public class CollectionItem  {
    private Integer id;
    private User user;
    private UploadMusic uploadMusic;

    public CollectionItem() {
    }

    public CollectionItem(User user, UploadMusic uploadMusic) {
        this.user = user;
        this.uploadMusic = uploadMusic;
    }

    @Override
    public String toString() {
        return "CollectionItem{" +
                "id=" + id +
                ", user=" + user +
                ", uploadMusic=" + uploadMusic +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UploadMusic getUploadMusic() {
        return uploadMusic;
    }

    public void setUploadMusic(UploadMusic uploadMusic) {
        this.uploadMusic = uploadMusic;
    }
}
