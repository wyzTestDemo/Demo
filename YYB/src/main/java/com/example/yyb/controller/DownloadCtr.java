package com.example.yyb.controller;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.service.MyUploadService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
public class DownloadCtr {
    @Autowired
    private MyUploadService myUploadService;
    @RequestMapping("/downloadLea")
    public ModelAndView downloadLea(@RequestParam(value = "pn",defaultValue = "1") Integer pn,HttpServletRequest request){
        ModelAndView downlaodData = new ModelAndView("downloadLea");
        UploadMusic uploadMusic = new UploadMusic();
        uploadMusic.setThrough(true);
        HttpSession session = request.getSession(false);
        User user = null;
        if(session!=null){
           user= (User) session.getAttribute("user");
        }
        PageHelper.startPage(pn, 10);
        List<UploadMusic> uploadAll = myUploadService.getUploadAll(uploadMusic,user);
        PageInfo pageInfo = new PageInfo<>(uploadAll,10);
        downlaodData.addObject("pageInfo", pageInfo);
        return downlaodData;
    }
    private List<UploadMusic> collectionList(List<UploadMusic> list,User user){

        return list;
    }
    @RequestMapping("/downMusic/{id}")
    public String downMusic(@PathVariable String id, HttpServletResponse response, HttpServletRequest request) {
        UploadMusic music = myUploadService.getUploadMusicById(id);
        if (music != null) {
            UploadMusic musicNum = new UploadMusic();
            musicNum.setId(music.getId());
            musicNum.setDownloadNum(music.getDownloadNum()+1);
            myUploadService.updateMusicStatus(musicNum);
            File file = new File(music.getMusicPath());
            String fileName = null;
            try {
                fileName= new String(music.getMusicName().getBytes("utf-8"), "ISO8859-1")+".mp3";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                response.setContentType("multipart/form-data");
                response.addHeader("Content-Disposition", "attachment;fileName=" +fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    OutputStream os = response.getOutputStream();
                    int len = 0;
                    while ((len = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}

