package com.example.tj_music.service;

import com.example.tj_music.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // get all user
    public List<User> getAll(){
        return userMapper.getAllUser();
    }

    //register
    //user_student_number,user_nickname,user_password
    public int register(String user_student_number, String user_nickname, String user_password){
        return userMapper.register(user_student_number, user_nickname, user_password);
    }

    public boolean login(String user_student_number, String user_password) {
        // check whether the password is correct
        User user = userMapper.getUserByStudentNumber(user_student_number);
        if(user == null){
            System.out.println("用户不存在");
            return false;
        }else{
            if(user.getUserPassword().equals(user_password)){
                return true;
            }else{
                return false;
            }
        }
    }

    public User getUserByStudentNumber(String user_student_number){
        return userMapper.getUserByStudentNumber(user_student_number);
    }
}
