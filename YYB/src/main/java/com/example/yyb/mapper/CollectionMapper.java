package com.example.yyb.mapper;

import com.example.yyb.entity.CollectionItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface CollectionMapper {
    @Insert("insert into collectionitem(userId,uploadMusicId) values(#{user.id},#{uploadMusic.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer addCollection(CollectionItem collectionItem);

    @Delete("delete from collectionitem where userId = #{user.id} and uploadMusicId = #{uploadMusic.id}")
    public Integer deleteCollection(CollectionItem collectionItem);
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "user",column = "userId",one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById",fetchType =FetchType.LAZY)),
            @Result(property = "uploadMusic",column = "uploadMusicId",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicById",fetchType =FetchType.LAZY))
    })
    @Select("select * from collectionitem where userId =#{userId}")
    public List<CollectionItem> getCollectionByUserId(@Param("userId")Integer id);
    @Select("select * from collectionitem where uploadMusicId =#{uploadMusicId}")
    public List<CollectionItem> getCollectionByUploadMusicId(@Param("uploadMusicId")String id);
}
