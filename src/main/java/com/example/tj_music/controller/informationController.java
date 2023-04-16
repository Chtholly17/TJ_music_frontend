package com.example.tj_music.controller;

import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.informationService;
import com.example.tj_music.utils.HttpUtils;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController // @RestController = @Controller + @ResponseBody (return json)
public class informationController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private informationService informationService;

    @GetMapping("/getUserImage")
    public Result getUserImage(HttpServletResponse resp){
        String url = "http://localhost:8080/test.jpg";
        // 获取输入流
        InputStream inputStream = HttpUtils.getInputStream(url);
        // 将输入流写入到response的输出流中
        HttpUtils.writeFile(resp, inputStream);
        return Result.success();
    }
    @GetMapping("/getuserInformation")
    public Result getUserInformation(@RequestParam("user_id") int user_id) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * 用这个API可以更新用户的信息
     * @param user_id: 用户的ID
     * @param new_name: 用户的新名字
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserName")
    public Result updateUserName(@RequestParam("user_id") int user_id, @RequestParam("new_name") String new_name) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserName(user.getUserStudentNumber(), new_name);
        return Result.success();
    }

    /**
     * 更新用户信息
     * 用这个API可以更新用户的信息
     * @param user_id: 用户的ID
     * @param new_password: 用户的新密码
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserPassword")
    public Result updateUserPassword(@RequestParam("user_id") int user_id, @RequestParam("new_password") String new_password) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserPassword(user.getUserStudentNumber(), new_password);
        return Result.success();
    }

    /**
     * 更新用户信息
     * 用这个API可以更新用户的信息
     * @param user_id: 用户的ID
     * @param new_signature: 用户的新签名
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserSignature")
    public Result updateUserSignature(@RequestParam("user_id") int user_id, @RequestParam("new_signature") String new_signature) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserSignature(user.getUserStudentNumber(), new_signature);
        return Result.success();
    }

    /**
     * 更新用户信息
     * 用这个API可以更新用户的信息
     * @param user_id: 用户的ID
     * @param new_ProfileImage: 用户的新文件名
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserProfileImage")
    public Result updateUserProfileImage(@RequestParam("user_id") int user_id, @RequestParam("new_ProfileImage") String new_ProfileImage,@RequestParam("file") MultipartFile file) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserProfileImageByStudentNumber(user.getUserStudentNumber(), new_ProfileImage);
        String fileName = file.getOriginalFilename();
        String filePath = "./src/main/resources/static/images/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return Result.success();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return Result.fail("upload failed");
    }
}