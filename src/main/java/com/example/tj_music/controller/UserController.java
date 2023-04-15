/*
 * @Description: 
 * @Date: 2023-04-04 15:46:22
 * @LastEditTime: 2023-04-15 18:41:50
 */
package com.example.tj_music.controller;


import com.example.tj_music.service.UserService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
