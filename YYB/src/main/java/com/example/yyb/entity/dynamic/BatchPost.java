package com.example.yyb.entity.dynamic;

import com.example.yyb.entity.Post;
import org.apache.ibatis.jdbc.SQL;

public class BatchPost {
    /*
  动态增加
   */
    public String dynamicPostAdd(Post post) {
        return new SQL() {{
            INSERT_INTO("post");
            if (post.getPostName() != null) {
                VALUES("postName", "#{postName}");
            }
            if (post.getPostContent() != null) {
                VALUES("postContent", "#{postContent}");
            }
            if (post.getPost() != null) {
                VALUES("postId", "#{post.id}");
            }
            if (post.getTimePost() != null) {
                VALUES("timePost", "#{timePost}");
            }
            if (post.getUser() != null) {
                VALUES("userId", "#{user.id}");
            }
        }}.toString();
    }

   /* public String dynamicPostSel(Post post) {
        return new SQL() {{
            if (post != null) {
                SELECT("*");
                FROM("post");
                if (post.getId() !=null) {
                    WHERE("id=#{id}");
                }
                if(post.getPostName()!=null){
                    WHERE("postName like CONCAT('%',#{postName},'%') order by timePost desc");
                }
            }

        }}.toString();
    }*/

}
