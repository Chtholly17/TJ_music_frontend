package com.example.tj_music.controller;

import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.informationService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class informationController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private informationService informationService;

    /**
     * 获取用户信息
     * 用这个API可以获取用户的信息
     * @param user_id: 用户的ID
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @GetMapping("/userInformation")
    public Result UserInformation(@RequestParam("user_id") int user_id) {
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
     * @param new_profile_image: 用户的新头像
     * @return: list of objects: [user_id, user_name, user_signature, user_profile_image]
     */
    @PostMapping("/updateUserProfileImage")
    public Result updateUserProfileImage(@RequestParam("user_id") int user_id, @RequestParam("new_profile_image") String new_profile_image) {
        User user = informationService.getInformationById(user_id);
        if (user == null) {
            return Result.fail("user not found");
        }
        informationService.updateUserProfileImageByStudentNumber(user.getUserStudentNumber(), new_profile_image);
        return Result.success();
    }
}