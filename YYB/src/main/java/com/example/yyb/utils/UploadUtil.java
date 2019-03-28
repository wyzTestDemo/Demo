package com.example.yyb.utils;

import com.example.yyb.entity.UploadMusic;
import com.example.yyb.entity.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UploadUtil {
    public static  String saveUserImag(MultipartFile file,User user,String url){
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        fileName = UUID.randomUUID().toString() + suffixName;
        String filePath =url+"/img/"+ fileName;
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        user.setImage(fileName);
        try {
            file.transferTo(dest);
            return "success";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    public static List<UploadMusic> getUploadFiles(
            MultipartHttpServletRequest multipartHttpServletRequest, String Path, User user) {
        List<MultipartFile> files = multipartHttpServletRequest
                .getFiles("files");
        List<UploadMusic> fileList = new ArrayList<UploadMusic>();
        for (MultipartFile file : files) {
            // 取得上传文件
            String fileName = file.getOriginalFilename();
            Long fileSize = file.getSize();
            if (null != fileName && !"".equals(fileName)) {
                try {
                    // 创建文件要保存的路径
                    File uploadFile = new File(Path);
                    if (!uploadFile.exists() || null == uploadFile) {
                        uploadFile.mkdirs();
                    }
                    // 获取文件类型
                    String fileType = fileName.substring(
                            fileName.lastIndexOf(".") + 1, fileName.length());
                    String id = UUID.randomUUID().toString();
                    String targetName = id + "." + fileType;
                    // 文件真实存放路径
                    String filePath = uploadFile.getPath() + File.separator
                            + targetName;
                    // 保存文件
                    file.transferTo(new File(filePath));
                    // 初始化上传文件
                    UploadMusic up = new UploadMusic(id, fileName.substring(0, fileName.lastIndexOf(".")), fileType,
                            fileSize + "", filePath,new Date());
                    up.setUser(user);
                    fileList.add(up);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return fileList;
    }
}
