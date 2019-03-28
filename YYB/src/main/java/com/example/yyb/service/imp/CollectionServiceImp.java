package com.example.yyb.service.imp;

import com.example.yyb.entity.CollectionItem;
import com.example.yyb.mapper.CollectionMapper;
import com.example.yyb.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
@Service
public class CollectionServiceImp implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;
    @Override
    public Integer addCollection(CollectionItem collectionItem) {
        return collectionMapper.addCollection(collectionItem);
    }
    @Override
    public Integer cancelCollection(CollectionItem collectionItem) {
        return collectionMapper.deleteCollection(collectionItem);
    }
}
