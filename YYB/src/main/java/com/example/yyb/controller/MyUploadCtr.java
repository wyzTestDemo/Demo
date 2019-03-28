package com.example.yyb.controller;

import com.example.yyb.entity.Post;
import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.service.MyUploadService;
import com.example.yyb.service.PostService;
import com.example.yyb.utils.PostingStorageUtil;
import com.example.yyb.utils.UploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyUploadCtr {
    @Value("${path}")
    private String path;
    @Autowired
    private MyUploadService myUploadService;
    @Autowired
    private PostService postService;


    private class ThreaDemo2 implements Runnable {

        @Override
        public void run() {
            System.out.println("播放你没");
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap testC(MultipartHttpServletRequest multiRequest, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<UploadMusic> fileList = UploadUtil.getUploadFiles(multiRequest, path, user);
        ModelMap model = new ModelMap();

        try {
            for (UploadMusic file : fileList) {
                Post post = null;
                System.out.println("上传文件有：" + file);
                post = PostingStorageUtil.getPost(file.getUser().getUserName() + "已成功上传音乐",

                        file.getMusicName(), request, null, null, null, path);
                Integer integer1 = postService.addPost(post);
                file.setPost(post);
            }
            Integer integer = myUploadService.addUploads(fileList);
            System.out.println("integer = " + integer);

            model.addAttribute("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("meaasge", "failed");
        }
        return model;
    }

    /*------------------------------------------------------------------*/
    @RequestMapping("/myupload/{token}")
    public ModelAndView myupload(@PathVariable("token") String id, @RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        ModelAndView upload = new ModelAndView("user/myupload");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            /*获取个人的上传w=文件信息并分页*/
            PageHelper.startPage(pn, 10);
            List<UploadMusic> uploadMusics = myUploadService.getUploadMusicsByUserId(user.getId());
            PageInfo pageInfo = new PageInfo<>(uploadMusics, 10);
            System.out.println(pageInfo);
            upload.addObject("pageInfo", pageInfo);
        } else {
            upload.setViewName("login/login");
        }
        return upload;
    }

}
