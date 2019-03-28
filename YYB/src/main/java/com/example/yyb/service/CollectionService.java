package com.example.yyb.service;

import com.example.yyb.entity.CollectionItem;

public interface CollectionService {
    public Integer addCollection(CollectionItem collectionItem);
    public Integer cancelCollection(CollectionItem collectionItem);
}
