package com.example.yyb.entity.dynamic;

import com.example.yyb.entity.UploadMusic;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class BatchUpload {
    public String batUploadAdd(Map map){
        List<UploadMusic> uploadMusics = (List<UploadMusic>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into uploadmusic(id,musicName,musicType,musicSize,musicPath,uploadTime,userId,postId) values");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].id},#'{'list[{0}].musicName},#'{'list[{0}].musicType},#'{'list[{0}].musicSize},#'{'list[{0}].musicPath},#'{'list[{0}].uploadTime},#'{'list[{0}].user.id},#'{'list[{0}].post.id})"
        );
        for (int i = 0; i < uploadMusics.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < uploadMusics.size() - 1) {
                sb.append(",");
            }
        }
        return  sb.toString();
    }
    /*动态更改*/
    public String dynamicUploadMusicUpd(UploadMusic uploadMusic) {
        return new SQL() {{
            UPDATE("uploadmusic");
            if(uploadMusic.getMusicName()!=null){
                SET("musicName=#{musicName}");
            }
            if (uploadMusic.getMusicType() != null) {
                SET("musicType = #{musicType}");
            }
            if (uploadMusic.getMusicSize() != null) {
                SET("musicSize = #{musicSize}");
            }
            if (uploadMusic.getMusicPath() != null) {
                SET("musicPath=#{musicPath}");
            }
            if (uploadMusic.getDownloadNum()>0) {
                SET("downloadNum=#{downloadNum}");
            }
            if (uploadMusic.isThrough()) {
                SET("through=#{through}");
            }
            if(uploadMusic.getUser()!=null){
                SET("userId=#{user.id}");
            }
            if(uploadMusic.getUploadTime()!=null){
                SET("uploadTime=#{uploadTime}");
            }
            WHERE("id =#{id}");
        }}.toString();
    }
    /*动态查询*/
    public String dynamicUplaodMusiSel(UploadMusic uploadMusic){
        return  new SQL(){{
            SELECT("*");
            FROM("uploadmusic");
            if(uploadMusic!=null) {
                if (uploadMusic.getId() != null) {
                    WHERE("id = #{id}");
                }
                if (uploadMusic.getMusicName() != null) {
                    WHERE("musicName=#{musicName}");
                }
                if (uploadMusic.isThrough()) {
                    WHERE("through=#{through}");
                }
                if (uploadMusic.getUser() != null) {
                    WHERE("userId = #{user.id}");

                }
            }
        }}.toString();
    }


}
