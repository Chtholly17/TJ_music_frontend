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
    @PostMapping("/login")  // @PostMapping = @RequestMapping + method = RequestMethod.POST
    public Result login(String id, String passwd) {
        Integer idInt = Integer.parseInt(id);
        if (userService.login(idInt, passwd)) {
            return Result.success();
        }
        else return Result.fail("Wrong id or password");
    }

    /**
     * 注册用户
     * 通过该API注册用户
     * @param id    用户id
     * @param passwd    用户密码
     * @param confirmedPasswd   确认密码
     * @param name  用户名
     * @param greet   提示
     * @return
     */
    @PostMapping("/register")
    public Result register(String id, String passwd,String confirmedPasswd, String name, String greet) {
        if (!passwd.equals(confirmedPasswd)) {
            return Result.fail("Password not match");
        }
        if(passwd.length() < 6) {
            return Result.fail("Password too short");
        }

        Integer idInt = Integer.parseInt(id);
        String res = userService.register(idInt, passwd, name, greet);
        if (res.equals("Register successfully")) {
            return Result.success();
        }
        else return Result.fail(res);
    }
}
