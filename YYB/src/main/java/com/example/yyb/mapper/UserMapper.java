package com.example.yyb.mapper;

import com.example.yyb.entity.User;
import com.example.yyb.entity.dynamic.BatchUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserMapper {
    /*删除用户*/
    @Delete("delete from user where id = #{id}")
    public Integer deleteUser(@Param("id") Integer id);
    /*搜索用户*/
    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "musicName", column = "musicName"),
                    @Result(property = "userName", column = "userName"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "image", column = "image"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "collectionItems",column = "id",many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "uploadMusics", column = "id", many = @Many(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "posts", column = "id", many = @Many(select = "com.example.yyb.mapper.PostMapper.getPostByUserId", fetchType = FetchType.LAZY))

            })
    @Select("select * from user where userName like CONCAT('%',#{userName},'%') order by registerTime desc")
    public List<User> getUserBySoso(@Param("userName") String userName);
    /*查找全部用户*/
    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "musicName", column = "musicName"),
                    @Result(property = "userName", column = "userName"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "image", column = "image"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "collectionItems",column = "id",many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "uploadMusics", column = "id", many = @Many(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "posts", column = "id", many = @Many(select = "com.example.yyb.mapper.PostMapper.getPostByUserId", fetchType = FetchType.LAZY))

            })
    @Select("select * from user")
    public List<User> getUserByAll();
    @SelectProvider(type = BatchUser.class,method = "batUserSel")
    public User dynaemicUserSel(User user);
    @UpdateProvider(type = BatchUser.class, method = "batUserUpdate")
    public Integer dynamicUserUp(User user);
    @InsertProvider(type = BatchUser.class, method = "batUserAdd")
    public Integer batUserAdd(User user);

    @Results(
            {
                    @Result(property = "id", column = "id"),
                    @Result(property = "musicName", column = "musicName"),
                    @Result(property = "userName", column = "userName"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "image", column = "image"),
                    @Result(property = "sex", column = "sex"),
                    @Result(property = "collectionItems",column = "id",many = @Many(select = "com.example.yyb.mapper.CollectionMapper.getCollectionByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "uploadMusics", column = "id", many = @Many(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByUserId", fetchType = FetchType.LAZY)),
                    @Result(property = "posts", column = "id", many = @Many(select = "com.example.yyb.mapper.PostMapper.getPostByUserId", fetchType = FetchType.LAZY))

            })
    @Select("select * from user where userName = #{userName} and password = #{password}")
    public User getUser(@Param("userName") String userName, @Param("password") String password);

    @Select("select count(*) from uploadmusic where id=#{id}")
    public Integer getUploadNum(Integer id);

    @Select("select id, userName,image,sex from user where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "image",column = "image"),
            @Result(property = "sex",column = "sex")
    }
    )
    public User getUserPostById(@Param("id") Integer id);
    @Select("select * from user where id = #{id}")
    public User getUserUploadById(@Param("id") Integer id);
}
