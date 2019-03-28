package com.example.yyb.service;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;

import java.util.List;

public interface MyUploadService {
    public List<UploadMusic> getUploadAll(UploadMusic uploadMusic, User user);
    public List<UploadMusic> getUploadMusicsByUserId(int userId);
    public List<UploadMusic> getUploadMusicByThroughAll();
    public UploadMusic getUploadMusicById(String id);
    public Integer addUploads(List<UploadMusic> uploadMusics);
    public Integer updateMusicStatus(UploadMusic uploadMusic);
    public List<UploadMusic> getUploadMusicBySoso(String musicName);
    public Integer deleteMusic(String id);
}
