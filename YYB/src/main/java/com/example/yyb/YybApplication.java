package com.example.yyb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@ServletComponentScan
@SpringBootApplication
@EnableCaching
@MapperScan("com.example.yyb.mapper")
public class YybApplication {

    public static void main(String[] args) {
        SpringApplication.run(YybApplication.class, args);
    }

}

