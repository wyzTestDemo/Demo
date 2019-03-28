package com.example.yyb.controller;

import com.example.yyb.entity.CollectionItem;
import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import com.example.yyb.service.CollectionService;
import com.example.yyb.service.MyUploadService;
import com.example.yyb.service.UserService;
import com.example.yyb.utils.SmsCheckUtil;
import com.example.yyb.utils.UploadUtil;
import com.example.yyb.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

@Controller
public class UserCtr {
    @Autowired
    private UserService userService;
    @Autowired
    private MyUploadService myUploadService;
    @Autowired
    private CollectionService collectionService;
    @Value("${path}")
    private String path;

    @RequestMapping(value = "/loginStatus", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        System.out.println(userName + "," + password);
        ModelAndView result = new ModelAndView("redirect:/index");
        User user = userService.login(userName, password);
        HttpSession session = request.getSession();
        if (user == null) {
            result.setViewName("login/login");
            result.addObject("loginStatus", "error");
        } else {
            session.setAttribute("user", user);
            session.setAttribute("token", user.getId() + "");
            if ("admin".equals(user.getRole())) {
                result.setViewName("redirect:/admin/selUserStatus");
            } else {
                result.addObject("loginStatus", "success");
            }
        }
        return result;
    }

    @RequestMapping(value = "/logout/{token}/{title}")
    public ModelAndView logout(HttpServletRequest request, @PathVariable("token") String myToken, @PathVariable("title") String title) {
        String url = title.substring(0, 1).toLowerCase().concat(title.substring(1));
        if (url.equals("mail") || url.equals("myUpload") || url.equals("userInfo")) {
            url = "index";
        }
        url = "redirect:/" + url;

        ModelAndView result = new ModelAndView(url);
        HttpSession session = request.getSession(false);
        if (session != null) {
            String token = (String) session.getAttribute("token");
            if (token.equals(myToken)) {
                session.removeAttribute("user");
            } else {
                result.setViewName("error");
            }
        }
        return result;
    }

    @RequestMapping("/myUser/{token}")
    public ModelAndView myUser(@PathVariable("token") String token) {
        ModelAndView myUser = new ModelAndView("user/userInfo");
        return myUser;
    }

    @RequestMapping("/myMail/{token}")
    public ModelAndView myMail(@PathVariable("token") String id) {
        ModelAndView mailData = new ModelAndView("user/mail");
        return mailData;
    }

    @RequestMapping("/myUserInfoImg/{id}")
    public void getUserImg(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getUserId(id);
        String imgPath = user.getImage();
        File file = new File(imgPath);
        if (!file.exists()) throw new RuntimeException("图像文件不存在 --> 404");
        BufferedInputStream bis = null;
        OutputStream os = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        bis = new BufferedInputStream(fileInputStream);
        response.setContentType("image/*");
        response.setContentLength(bis.available());
        os = response.getOutputStream();
        int n;
        while ((n = bis.read(buffer)) != -1) {
            os.write(buffer, 0, n);
        }
        bis.close();
        os.flush();
        os.close();
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap registerUser(User user, @RequestParam("validateMSG") String validateMSG, HttpServletRequest request) {
        ModelMap result = new ModelMap();
        user.setRegisterTime(new Date());
        HttpSession session = request.getSession(false);
        if (session != null) {
            /*获取验证码*/
            String chckMS = (String) session.getAttribute("checkMS");
            if (chckMS.equals(validateMSG)) {


                if (UserUtil.validationUser(user)) {
                    int registerResult = userService.batUserAdd(user);
                    result.addAttribute("success", "success");
                } else {
                    result.addAttribute("success", "账号或密码格式错误");
                }
            }else{
                result.addAttribute("success","验证码输入错误,请重新获取" );
            }
        }
        return result;
    }

    @RequestMapping("/sendMSG")
    @ResponseBody
    public ModelMap sendMSG(@RequestParam(value = "phone", defaultValue = "00000000000") String phone, HttpServletRequest request) {
        ModelMap result = new ModelMap();
        String checkMS = SmsCheckUtil.getCheckMS();
        HttpSession session = request.getSession(true);
        Integer minute = 2;
        String[] params = {checkMS, minute.toString()};
        User user = new User();
        user.setUserName(phone);
        if (userService.checkUser(user) == null) {
            result.addAttribute("status", "success");
            /*发送短信验证码*/
            SmsCheckUtil.sendMessage(phone, params);
            session.setAttribute("checkMS", checkMS);
            System.out.println(checkMS);
            /*失效时间*/
            SmsCheckUtil.removeAttrbute(session, "checkMS", minute);
        } else {
            result.addAttribute("status", "error");
            result.addAttribute("error", "该手机号码已经被注册");
        }

        return result;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap upUser(User user, HttpServletRequest request) {
        ModelMap resutl = new ModelMap();
        System.out.println(user);
        HttpSession session = request.getSession(false);
        User oldUser = (User) session.getAttribute("user");
        user.setId(oldUser.getId());
        Integer integer = userService.updateUserInfo(user);
        System.out.println(integer);
        if (integer > 0) {
            resutl.addAttribute("updateStatus", "success");
            session.setAttribute("user", user);
        }
        return resutl;
    }

    @RequestMapping(value = "/changeUserImage", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap changeUserImag(MultipartFile file, HttpServletRequest request) {
        ModelMap result = new ModelMap();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String saveResult = UploadUtil.saveUserImag(file, user,path);
        System.out.println(user);
        Integer integer = userService.updateUserInfo(user);
        result.addAttribute("saveResutl", saveResult);
        return result;
    }

    @RequestMapping(value = "/collectionMusic", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap collectionMusic(String uploadMusicId, HttpServletRequest request) {
        System.out.println("收藏");
        ModelMap result = new ModelMap();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            UploadMusic uploadMusic = myUploadService.getUploadMusicById(uploadMusicId);
            CollectionItem collectionItem = new CollectionItem(user, uploadMusic);
            Integer integer = collectionService.addCollection(collectionItem);
        }
        return result;
    }

    @RequestMapping(value = "/cancelCollection", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap cancelCollection(String uploadMusicId, HttpServletRequest request) {
        System.out.println("取消");
        ModelMap result = new ModelMap();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            UploadMusic uploadMusic = myUploadService.getUploadMusicById(uploadMusicId);
            CollectionItem collectionItem = new CollectionItem(user, uploadMusic);
            Integer integer = collectionService.cancelCollection(collectionItem);
        }
        return result;
    }
}
