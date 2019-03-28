package com.example.yyb.entity.dynamic;

import com.example.yyb.entity.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Random;

public class BatchUser {
    public String batUserUpdate(User user) {
        return new SQL() {{
            if (user != null) {
                UPDATE("user");

                if (user.getUserName() != null) {
                    SET("userName=#{userName}");
                }
                if(user.getImage()!=null){
                    SET("image=#{image}");
                }
                if(user.getPassword()!=null){
                    SET("password=#{password}");
                }
                if(user.getSex()!=null){
                    SET("sex=#{sex}");
                }
                if(!user.getBirth().equals("")){
                    SET("birth=#{birth}");
                }
                if(user.getIntroduction()!=null){
                    SET("introduction=#{introduction}");
                }
                WHERE("id=#{id}");
            }
        }}.toString();
    }

    public String batUserAdd(User user) {
        return new SQL() {
            {
                if (user != null) {
                    INSERT_INTO("user");
                    if (user.getId() != 0) {
                        VALUES("id", "#{id}");
                    }
                    if (user.getUserName() != null) {
                        VALUES("userName", "#{userName}");
                    }
                    if (user.getPassword() != null) {
                        VALUES("password", "#{password}");
                    }
                    if(user.getPhone()!=null){
                        VALUES("phone","#{phone}" );
                    }else {
                        VALUES("phone","#{userName}" );
                    }
                    if (user.getImage() != null) {
                        VALUES("image", "#{password}");
                    } else {
                        /*/static/img/user/ 使用了nginx映射本地地址，只需名称即可*/
                        VALUES("image", "\'my_user.jpg\'");
                    }
                    if (user.getSex() != null) {
                        VALUES("sex", "#{sex}");
                    } else {
                        Random random = new Random();
                        char[] sex = new char[]{'M', 'F'};
                        int i = random.nextInt(2);
                        VALUES("sex", "\'" + sex[i] + "\'");
                    }
                    if (user.getBirth() != null) {
                        VALUES("birth", "#{birth}");
                    } else {
                        String birth = "\'97-07-07\'";
                        VALUES("birth", birth);
                    }
                    if (user.getIntroduction() != null) {
                        VALUES("introduction", "#{introduction}");
                    }
                    if (user.getRegisterTime() != null) {
                        VALUES("registerTime", "#{registerTime}");
                    }
                    if (user.getRole() != null) {
                        VALUES("role", "#{role}");
                    } else {
                        VALUES("role", "\'user\'");
                    }
                }
            }

        }.toString();
    }
    public String batUserSel(User user){
        return new SQL(){{
            if(user!=null){
                SELECT("*");
                FROM("user");
                if(user.getUserName()!=null){
                    WHERE("userName=#{userName}");
                }
                if(user.getPassword()!=null){
                    WHERE("password=#{password}");
                }
                if(user.getImage()!=null){
                    WHERE("image=#{image}");
                }
                if(user.getRegisterTime()!=null){
                    WHERE("registerTime=#{registerTime}");
                }
                if(user.getBirth()!=null){
                    WHERE("birth=#{birth}");
                }
                if(user.getSex()!=null){
                    WHERE("sex=#{sex}");
                }
                if(user.getIntroduction()!=null){
                    WHERE("introduction = #{introduction}");
                }
                if(user.getRole()!=null){
                    WHERE("role=#{role}");
                }
            }


        }}.toString();
    }
}
