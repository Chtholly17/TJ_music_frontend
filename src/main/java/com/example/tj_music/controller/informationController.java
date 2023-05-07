package com.example.tj_music.controller;

import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.informationService;
import com.example.tj_music.utils.HttpUtils;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


@RestController // @RestController = @Controller + @ResponseBody (return json)
@PropertySource(value = {"classpath:application.properties"})
public class informationController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private informationService informationService;

    @Value("${imagepath}")
    private String imagepath;

    /**
     * 获取用户信息
     * 用这个API可以获取用户信息
     * @param user_student_number
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam("user_student_number") String user_student_number) {
        User user = informationService.getInformationByStudentNumber(user_student_number);
        if (user == null) {
            return Result.fail("user not found");
        }
        return Result.success(user);
    }

    /**
     * 获取用户头像
     * @param user_student_number
     * @return
     */
    @GetMapping("/getUserImage")
    public Result getUserImage(@RequestParam("user_student_number") String user_student_number) {
        User user = informationService.getInformationByStudentNumber(user_student_number);
        if (user == null) {
            return Result.fail("user not found");
        }
        return Result.success(user.getUserProfileImageFilename());
    }

    /**
     * 修改用户信息
     * 用这个API可以修改用户信息
     * @param user_student_number
     * @param new_nickname
     * @param new_college
     * @param new_major
     * @param new_area1
     * @param new_area2
     * @param new_birthday
     * @param new_gender
     * @param new_signature
     * @return
     */
    @PostMapping("/updateUserinfo")
    public Result updateUserinfo(@RequestParam("user_student_number") String user_student_number,
                                 @RequestParam("new_nickname") String new_nickname,
                                 @RequestParam("new_college") String new_college,
                                 @RequestParam("new_major") String new_major,
                                 @RequestParam("new_area1") String new_area1,
                                 @RequestParam("new_area2") String new_area2,
                                 @RequestParam("new_birthday") Date new_birthday,
                                 @RequestParam("new_gender") String new_gender,
                                 @RequestParam("new_signature") String new_signature) {
        User user = informationService.getInformationByStudentNumber(user_student_number);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserNickName(new_nickname, user_student_number);
        informationService.updateUserCollege(new_college, user_student_number);
        informationService.updateUserMajor(new_major, user_student_number);
        informationService.updateUserArea1(new_area1, user_student_number);
        informationService.updateUserArea2(new_area2, user_student_number);
        informationService.updateUserBirthday(new_birthday, user_student_number);
        informationService.updateUserGender(new_gender, user_student_number);
        informationService.updateUserSignature(new_signature, user_student_number);
        return Result.success();
    }


    /**
     * 修改用户头像
     * 用这个API可以修改用户头像
     * @param user_student_number
     * @param file
     * @return
     */
    @PostMapping("/updateUserImage")
    public Result updateUserImage(@RequestParam("user_student_number") String user_student_number,
                                  @RequestParam("file") MultipartFile file) {
        User user = informationService.getInformationByStudentNumber(user_student_number);
        if (user == null) {
            return Result.fail("user not found");
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(imagepath + fileName);
        if(!dest.exists()){
            //先得到文件的上级目录，并创建上级目录，在创建文件
            dest.getParentFile().mkdir();
            try {
                //创建文件
                dest.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(dest);
        try {
            file.transferTo(dest);
            String url = "http://localhost:8080/" + fileName;
            informationService.updateUserProfileImage(url, user_student_number);
            return Result.success(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail("upload failed");
    }

    /**
     * 修改用户密码
     * 用这个API可以修改用户密码
     * @param user_student_number: 用户的学号
     * @param new_password: 用户的新密码
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserPassword")
    public Result updateUserPassword(@RequestParam("user_student_number") String user_student_number,
                                     @RequestParam("new_password") String new_password) {
        User user = informationService.getInformationByStudentNumber(user_student_number);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserPassword(new_password, user_student_number);
        return Result.success();
    }

    /**
     * get user information.
     * code:1 represents getting user information successfully.
     * code:0 represents getting user information failed. The account does not exist.
     * @param userNumber
     * @return Result, data is a dictionary consist two keys: 'user' and 'workList'
     */
    @GetMapping("/UserProfile")
    public Result getUserProfile(@RequestParam("userNumber") String userNumber) {
        return informationService.getUserInformation(userNumber);
    }
}