package com.example.tj_music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.example.tj_music.Result.Result;
import com.example.tj_music.service.UserService;
@RestController // @RestController = @Controller + @ResponseBody (return json)
public class UserController{

    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    // user service
    @Autowired // auto-inject
    private UserService userService;

    /**
     * 用户注册
     * 通过这个API实现用户的注册功能
     * @param user_student_number 用户学号
     * @param user_nickname 用户昵称
     * @param user_password 用户密码
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestParam("user_student_number") String user_student_number,
                           @RequestParam("user_nickname") String user_nickname,
                           @RequestParam("user_password") String user_password){

        try{
            userService.register(user_student_number,user_nickname,user_password);
            return Result.success();
        }catch (Exception e){
            log.error(e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

}
