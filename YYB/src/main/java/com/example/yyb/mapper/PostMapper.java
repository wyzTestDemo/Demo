package com.example.yyb.mapper;

import com.example.yyb.entity.Post;
import com.example.yyb.entity.dynamic.BatchPost;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
public interface PostMapper {
    /*搜索查询*/
    @Select("select * from post where postName like CONCAT('%',#{postName},'%') order by timePost desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "postName", column = "postName"),
            @Result(property = "postContent", column = "postContent"),
            @Result(property = "timePost", column = "timePost"),
            @Result(property = "posts", column = "id", many = @Many(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.PostMapper.getPostByPostId")),
            @Result(property = "uploadMusic",column = "id",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByPostId",fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserPostById",fetchType = FetchType.LAZY))
    })
    public List<Post> getPostSosoResult(Post post);
    /*首页热帖显示*/
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "uploadMusic",column = "id",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByPostId",fetchType = FetchType.LAZY)),
            @Result(property = "posts", column = "id", many = @Many(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.PostMapper.getPostByPostId")),
            @Result(property = "user",column = "userId",one = @One(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.UserMapper.getUserPostById"))
    })
    @Select("select p.*,count(*) r from post p,post pc where p.id = pc.postId GROUP BY id ORDER BY timePost desc,r desc LIMIT 0,5")
    public List<Post> getPostPIndex();
    /*首页问答显示*/
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "uploadMusic",column = "id",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByPostId",fetchType = FetchType.LAZY)),
            @Result(property = "posts", column = "id", many = @Many(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.PostMapper.getPostByPostId")),
            @Result(property = "user",column = "userId",one = @One(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.UserMapper.getUserPostById"))
    })
    @Select("select * from post p where postId is null and (select count(*) from uploadmusic where postId = p.id)=0 and postName is not null order by timePost desc LIMIT 0,5")
    public List<Post> getPostQuestionIndex();
    /*评论显示*/
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uploadMusic",column = "id",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByPostId",fetchType = FetchType.LAZY)),
            @Result(property = "posts", column = "id", many = @Many(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.PostMapper.getPostByPostId")),
            @Result(property = "user",column = "userId",one = @One(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.UserMapper.getUserPostById"))
    })
    @Select("select * from post where id = #{id}")
    public Post getPostById(@Param("id") Integer id);

    @Results({
            @Result(property = "user",column = "userId",one = @One(select = "com.example.yyb.mapper.UserMapper.getUserPostById",fetchType = FetchType.LAZY))
    })
    @Select("select * from post where postId = #{postId} order by timePost desc")
    public List<Post> getPostByPostId(@Param("postId") Integer postId);
    /*关联查询*/
    @Select("select * from post where userid = #{userid}")
    public List<Post> getPostByUserId(@Param("userid") Integer userid);
    /*帖子的显示*/
    @Select("select * from post where postName is not null order by timePost desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "postName", column = "postName"),
            @Result(property = "postContent", column = "postContent"),
            @Result(property = "timePost", column = "timePost"),
            @Result(property = "posts", column = "id", many = @Many(fetchType = FetchType.LAZY,select = "com.example.yyb.mapper.PostMapper.getPostByPostId")),
            @Result(property = "uploadMusic",column = "id",one = @One(select = "com.example.yyb.mapper.UploadMusicMapper.getUploadMusicByPostId",fetchType = FetchType.LAZY)),
            @Result(property = "user", column = "userId", one = @One(select = "com.example.yyb.mapper.UserMapper.getUserPostById",fetchType = FetchType.LAZY))
    })
    public List<Post> getPostAll();

    @Insert("insert into post(postName,postContent,postId,timePost,userId) values(#{postName},#{postContent},#{post.id},#{timePost},#{user.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer addPost(Post post);
    @InsertProvider(type = BatchPost.class, method = "dynamicPostAdd")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer dynamicPostAdd(Post post);
}
