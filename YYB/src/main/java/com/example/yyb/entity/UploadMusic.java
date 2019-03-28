package com.example.yyb.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UploadMusic  {
    private String id;
    private String musicName;
    private String musicType;
    private String musicSize;
    private String musicPath;
    private Integer downloadNum;
    private boolean through;
    private Date uploadTime;
    private User user;
    private List<CollectionItem> collectionItems;
    private Post post;
    /*一个收藏状态*/
    private Boolean collectionStatus;

    @Override
    public String toString() {
        return "UploadMusic{" +
                "id='" + id + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicType='" + musicType + '\'' +
                ", musicSize='" + musicSize + '\'' +
                ", musicPath='" + musicPath + '\'' +
                ", downloadNum=" + downloadNum +
                ", through=" + through +
                ", uploadTime=" + uploadTime +
                ", user=" + user +
                ", post=" + post +
                ", collectionStatus=" + collectionStatus +
                '}';
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Boolean collectionStatus) {
        this.collectionStatus = collectionStatus;
    }


    public List<CollectionItem> getCollectionItems() {
        return collectionItems;
    }

    public void setCollectionItems(List<CollectionItem> collectionItems) {
        this.collectionItems = collectionItems;
    }

    public boolean isThrough() {
        return through;
    }

    public void setThrough(boolean through) {
        this.through = through;
    }

    public String getUploadTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        String formatDate = null;
        if(uploadTime!=null){
            formatDate = format.format(uploadTime);
        }
        return formatDate;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public UploadMusic() {
    }


    public String getMusicName() {
        return musicName;
    }

    public String getMusicType() {
        return musicType;
    }

    public String getMusicSize() {
        return musicSize;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public User getUser() {
        return user;
    }


    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public void setMusicSize(String musicSize) {
        this.musicSize = musicSize;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public UploadMusic(String id, String musicName, String musicType, String musicSize, String musicPath,Date uploadTime) {
        this.id = id;
        this.musicName = musicName;
        this.musicType = musicType;
        this.musicSize = musicSize;
        this.musicPath = musicPath;
        this.uploadTime = uploadTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
