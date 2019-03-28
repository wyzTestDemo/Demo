package com.example.yyb.service.imp;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.mapper.UploadMusicMapper;
import com.example.yyb.service.MyUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyUploadServiceImp implements MyUploadService {
    @Autowired
    private UploadMusicMapper uploadMusicMapper;
    @Override
    public List<UploadMusic> getUploadAll(UploadMusic uploadMusic, User user) {
        return uploadMusicMapper.getUploadMusicAll(uploadMusic,user);
    }
    @Override
    public List<UploadMusic> getUploadMusicsByUserId(int userId) {

        return uploadMusicMapper.getUploadMusicByUserId(userId);
    }
    @Override
    public List<UploadMusic> getUploadMusicByThroughAll() {
        return uploadMusicMapper.getUploadMusicByThroughAll();
    }
    @Override
    public UploadMusic getUploadMusicById(String id) {
        return uploadMusicMapper.getUploadMusicById(id);
    }
    @Override
    public Integer addUploads(List<UploadMusic> uploadMusics) {
        return uploadMusicMapper.addUploads(uploadMusics);
    }
    @Override
    public Integer updateMusicStatus(UploadMusic uploadMusic) {
        return uploadMusicMapper.updateMusicStatus(uploadMusic);
    }

    @Override
    public List<UploadMusic> getUploadMusicBySoso(String musicName) {
        return uploadMusicMapper.getUploadMusicBySoso(musicName);
    }

    @Override
    public Integer deleteMusic(String id) {
        return uploadMusicMapper.deleteUploadMusic(id);
    }
}
