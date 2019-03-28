package com.example.yyb.controller;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.service.MyUploadService;
import com.example.yyb.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminYYBCtr {
    @Autowired
    private MyUploadService myUploadService;
    @Autowired
    private UserService userService;
    /*删除用户*/
    @RequestMapping("/selUserStatus/delUser/{id}")
    public ModelAndView delUser(@PathVariable("id")Integer id){
        ModelAndView result = new ModelAndView("redirect:/admin/selUserStatus");
        Integer delResult = userService.deleteUser(id);
        if(delResult<=0){
            System.out.println("删除失败");
        }
        return result;
    }
    /*重置密码 346475*/
    @RequestMapping("/selUserStatus/resetUserPassword")
    public ModelAndView resetUserPassword(@RequestParam("id") Integer id) {
        ModelAndView result = new ModelAndView("redirect:/admin/selUserStatus");
        User UpUser = userService.getUserId(id);
        if (UpUser != null) {
            User user = new User();
            user.setPassword("4b7ab6");
            user.setId(id);
            System.out.println(user);
            Integer upResult = userService.updateUserInfo(user);
            if (upResult <= 0) {
                System.out.println("更改失败，没找到该用户");
            }
            /*发送短信通知用户*/
        }else {
            result.setViewName("error");
        }
        return result;
    }

    /*用户搜索*/
    @RequestMapping("/selUserStatus/selUserStatusSoso")
    public ModelAndView selUserStatusSoso(@RequestParam("soso") String soso, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView result = new ModelAndView("user/admin/adminIndex");
        PageHelper.startPage(pn, 10);
        List<User> users = userService.getUserBySoso(soso);
        PageInfo pageInfo = new PageInfo<>(users, 10);
        result.addObject("pageInfo", pageInfo);
        result.addObject("dq", "soso");
        result.addObject("soso", soso);
        return result;
    }

    /*站内文件删除*/
    @RequestMapping("/inFile/deleteFile/{id}")
    public ModelAndView deleteFile(@PathVariable("id") String id) {
        ModelAndView result = new ModelAndView("redirect:/admin/inFile");
        Integer delResult = myUploadService.deleteMusic(id);
        if (delResult <= 0) {
            System.out.println("删除失败，没找到改文件");
        }
        return result;
    }

    /*站内搜索文件*/
    @RequestMapping("/inFile/inFileSoso")
    public ModelAndView inFileSoso(@RequestParam("soso") String soso, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView result = new ModelAndView("user/admin/inFile");
        PageHelper.startPage(pn, 10);
        List<UploadMusic> uploadMusics = myUploadService.getUploadMusicBySoso(soso);
        PageInfo pageInfo = new PageInfo<>(uploadMusics, 10);
        result.addObject("pageInfo", pageInfo);
        result.addObject("dq", "inFileSoso");
        result.addObject("soso", soso);
        return result;
    }

    /*站内文件*/
    @RequestMapping("/inFile")
    public ModelAndView inFile(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView result = new ModelAndView("user/admin/inFile");
        PageHelper.startPage(pn, 10);
        /*已通过审核的*/
        UploadMusic uploadMusic = new UploadMusic();
        uploadMusic.setThrough(true);
        List<UploadMusic> uploadAll = myUploadService.getUploadAll(uploadMusic, null);
        PageInfo pageInfo = new PageInfo<>(uploadAll, 10);
        result.addObject("pageInfo", pageInfo);
        result.addObject("dq", "inFile");
        return result;
    }

    @RequestMapping("/message")
    public ModelAndView message(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView result = new ModelAndView("user/admin/message");
        PageHelper.startPage(pn, 10);
        List<UploadMusic> list = myUploadService.getUploadMusicByThroughAll();
        PageInfo pageInfo = new PageInfo<>(list, 10);
        result.addObject("pageInfo", pageInfo);
        return result;
    }

    @RequestMapping("/selUserStatus")
    public ModelAndView selUserStatus(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView result = new ModelAndView("user/admin/adminIndex");
        PageHelper.startPage(pn, 10);
        List<User> users = userService.getUserByAll();
        PageInfo pageInfo = new PageInfo<>(users, 10);
        result.addObject("pageInfo", pageInfo);
        result.addObject("dq", "selUserStatus");
        return result;
    }

    /*更改上传文件的状态*/
    @RequestMapping("/updateMusicStatus")
    public ModelAndView updateMusicStatus(String id, boolean status, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        ModelAndView view = new ModelAndView("user/admin/message");
        UploadMusic uploadMusic = myUploadService.getUploadMusicById(id);
        uploadMusic.setThrough(true);
        Integer result = myUploadService.updateMusicStatus(uploadMusic);
        System.out.println(result);
        PageHelper.startPage(pn, 10);
        List<UploadMusic> list = myUploadService.getUploadMusicByThroughAll();
        PageInfo pageInfo = new PageInfo<>(list, 10);
        view.addObject("pageInfo", pageInfo);
        return view;
    }
}
