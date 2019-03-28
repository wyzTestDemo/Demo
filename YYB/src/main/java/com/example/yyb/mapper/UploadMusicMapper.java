package com.example.yyb.mapper;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.entity.dynamic.BatchUpload;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UploadMusicMapper {
    /*搜索*/
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "post", column = "postId", one = @One(select = "com.example.yyb.mapper.PostMapper.getPostById", fetchType = FetchType.LAZY)),
            @Result(property = "collectionItems", column = "id", many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUploadMusicId", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById", fetchType = FetchType.LAZY))
    })
    @Select("select * from uploadMusic where musicName like CONCAT('%',#{musicName},'%') and through=1 order by uploadTime desc")
    public List<UploadMusic> getUploadMusicBySoso(@Param("musicName") String musicName);
    @Select("select * from uploadmusic where postId = #{id}")
    public UploadMusic getUploadMusicByPostId(Integer id);
    /*查找全部*/
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "post", column = "postId", one = @One(select = "com.example.yyb.mapper.PostMapper.getPostById", fetchType = FetchType.LAZY)),
            @Result(property = "collectionItems", column = "id", many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUploadMusicId", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById", fetchType = FetchType.LAZY))
    })
    @Select("select *,(select count(*) from collectionitem where userId = #{user.id} and uploadMusicId = u.id)collectionStatus from uploadmusic u where through=#{uploadMusic.through}")
    public List<UploadMusic> getUploadMusicAll(@Param("uploadMusic") UploadMusic uploadMusic, @Param("user") User user);


    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "collectionStatus",column = "collectionStatus"),
            @Result(property = "collectionItems", column = "id", many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUploadMusicId", fetchType = FetchType.LAZY)),
            @Result(property = "post", column = "postId", one = @One(select = "com.example.yyb.mapper.PostMapper.getPostById", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById", fetchType = FetchType.LAZY))
    })
    @Select("select *,(select count(*) from collectionitem where userId = #{userid} and uploadMusicId = u.id)collectionStatus  from uploadmusic u where userid = #{userid} order by uploadTime desc")
    public List<UploadMusic> getUploadMusicByUserId(@Param("userid") Integer userid);
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "collectionStatus",column = "collectionStatus"),
            @Result(property = "collectionItems", column = "id", many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUploadMusicId", fetchType = FetchType.LAZY)),
            @Result(property = "post", column = "postId", one = @One(select = "com.example.yyb.mapper.PostMapper.getPostById", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById", fetchType = FetchType.LAZY))
    })
    @Select("select * from uploadmusic where id = #{id}")
    public UploadMusic getUploadMusicById(@Param("id") String id);

    @InsertProvider(type = BatchUpload.class, method = "batUploadAdd")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer addUploads(List<UploadMusic> uploadMusics);

    @UpdateProvider(type = BatchUpload.class, method = "dynamicUploadMusicUpd")
    public Integer updateMusicStatus(UploadMusic uploadMusic);

    @Results({
            @Result(property = "post", column = "postId", one = @One(select = "com.example.yyb.mapper.PostMapper.getPostById", fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserUploadById", fetchType = FetchType.LAZY))
    })
    @Select("select * from uploadmusic where through=0 order by uploadTime desc")
    public List<UploadMusic> getUploadMusicByThroughAll();
    /*删除站内文件*/
    @Delete("delete from uploadmusic where id = #{id}")
    public Integer deleteUploadMusic(String id);
}
