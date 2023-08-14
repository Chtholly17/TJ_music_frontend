package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Image;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.informationService;
import com.example.tj_music.utils.ImageUtils;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;


@RestController // @RestController = @Controller + @ResponseBody (return json)
public class informationController {
    // user service
    @Autowired // auto-inject
    private informationService informationService;

    @Autowired
    private ImageUtils imageUtils;

    /**
     * 获取用户信息
     * 用这个API可以获取用户信息
     * @param userStudentNumber
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam("userStudentNumber") String userStudentNumber) {
        User user = informationService.getInformationByStudentNumber(userStudentNumber);
        if (user == null) {
            return Result.fail("user not found");
        }
        return Result.success(user);
    }

    /**
     * 获取用户头像
     * @param userStudentNumber
     * @return
     */
    @GetMapping("/getUserImage")
    public Result getUserImage(@RequestParam("userStudentNumber") String userStudentNumber) {
        User user = informationService.getInformationByStudentNumber(userStudentNumber);
        if (user == null) {
            return Result.fail("user not found");
        }
        System.out.println("user id is " + user.getUserId());
        return Result.success(user.getUserProfileImageFilename());
    }

    /**
     * 修改用户信息
     * 用这个API可以修改用户信息
     * @param userStudentNumber
     * @param newNickname
     * @param newCollege
     * @param newMajor
     * @param newArea1
     * @param newArea2
     * @param newBirthday
     * @param newGender
     * @param newSignature
     * @return
     */
    @PostMapping("/updateUserinfo")
    public Result updateUserinfo(@RequestParam("userStudentNumber") String userStudentNumber,
                                 @RequestParam("newNickname") String newNickname,
                                 @RequestParam("newCollege") String newCollege,
                                 @RequestParam("newMajor") String newMajor,
                                 @RequestParam("newArea1") String newArea1,
                                 @RequestParam("newArea2") String newArea2,
                                 @RequestParam("newBirthday")String newBirthday,
                                 @RequestParam("newGender") String newGender,
                                 @RequestParam("newSignature") String newSignature) throws ParseException {
        User user = informationService.getInformationByStudentNumber(userStudentNumber);
        // print out the userStudentNumber and newNickname
//        System.out.println("get the userStudentNumber: " + userStudentNumber);
//        System.out.println("get the newNickname: " + newNickname);
//        System.out.println("get the birthday: " + newBirthday);
        if (user == null) {
            return Result.fail("user not found");
        }
//        System.out.println(newBirthday);
        informationService.updateUserNickName(newNickname, userStudentNumber);
        informationService.updateUserCollege(newCollege, userStudentNumber);
        informationService.updateUserMajor(newMajor, userStudentNumber);
        informationService.updateUserArea1(newArea1, userStudentNumber);
        informationService.updateUserArea2(newArea2, userStudentNumber);
        if (!Objects.equals(newBirthday, "")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(newBirthday);
            informationService.updateUserBirthday(date, userStudentNumber);
        }
        informationService.updateUserGender(newGender, userStudentNumber);
        informationService.updateUserSignature(newSignature, userStudentNumber);
        return Result.success();
    }


    /**
     * 修改用户头像
     * 用这个API可以修改用户头像
     * @param image
     * @return
     */
    @PostMapping("/updateUserImage")
    public Result updateUserImage(Image image) {
        // print out the userStudentNumber and file
        System.out.println(image.getUser_student_number());
        System.out.println(image.getFile());
        String userStudentNumber = image.getUser_student_number();
        MultipartFile file = image.getFile();
        User user = informationService.getInformationByStudentNumber(userStudentNumber);
        if (user == null) {
            return Result.fail("user not found");
        }
        try{
            String url = imageUtils.upload(file, userStudentNumber, "avatar");
            informationService.updateUserProfileImage(url, userStudentNumber);
            return Result.success("upload success");
        } catch (IOException e) {
            return Result.fail("upload failed");
        }
    }

    /**
     * 修改用户密码
     * 用这个API可以修改用户密码
<<<<<<< HEAD
     * @param userStudentNumber: 用户的学号
     * @param newPassword: 用户的新密码
=======
     * @param user_student_number: 用户的学号
     * @param new_password: 用户的新密码
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
     * @return: list of objects: [userId, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserPassword")
    public Result updateUserPassword(@RequestParam("userStudentNumber") String userStudentNumber,
                                     @RequestParam("newPassword") String newPassword) {
        User user = informationService.getInformationByStudentNumber(userStudentNumber);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserPassword(newPassword, userStudentNumber);
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
