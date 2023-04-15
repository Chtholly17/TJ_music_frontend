/*
 * @Description: 
 * @Date: 2023-04-04 15:46:22
 * @LastEditTime: 2023-04-15 18:41:50
 */
package com.example.tj_music.controller;


import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.UserService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController // @RestController = @Controller + @ResponseBody (return json)
public class UserController{

    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private UserService userService;


    @GetMapping("/login")
    public Result login(@RequestParam("user_student_number") String user_student_number,
                        @RequestParam("user_password") String user_password){
        // check the parameter
        if(user_student_number == null || user_student_number.equals("")){
            return Result.fail("学号不能为空");
        }
        if(user_password == null || user_password.equals("")){
            return Result.fail("密码不能为空");
        }
        // check the user
        // if the user is not exist, return the error message
        // if the user is exist, return the user information
        boolean successful = userService.login(user_student_number, user_password);
        return Result.success();
    }


    @PostMapping("/register")
    public Result register(@RequestParam("user_student_number") String user_student_number,
                           @RequestParam("user_nickname") String user_nickname,
                           @RequestParam("user_password") String user_password){

        // check the parameter
        if(user_student_number == null || user_student_number.equals("")){
            return Result.fail("学号不能为空");
        }
        if(user_nickname == null || user_nickname.equals("")){
            return Result.fail("昵称不能为空");
        }
        if(user_password == null || user_password.equals("")){
            return Result.fail("密码不能为空");
        }
        // check the user
        // if the user student number is exist, return error message
        User user = userService.getUserByStudentNumber(user_student_number);
        if(user != null){
            return Result.fail("用户已存在");
        }

        return Result.success();
    }

}
