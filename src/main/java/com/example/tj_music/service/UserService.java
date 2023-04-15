package com.example.tj_music.service;

import com.example.tj_music.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // register a new user
    public void register(String user_student_number,String user_nickname,String user_password){
        userMapper.register(user_student_number,user_nickname,user_password);
    }

    // get user by student number
    public User getUserByStudentNumber(String user_student_number){
        return userMapper.getUserByStudentNumber(user_student_number);
    }


}
