package com.example.tj_music.service;

import com.example.tj_music.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.db.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public boolean login(Integer id, String passwd) {
        String passwdInDB = userMapper.getPasswdById(id);
        if (passwdInDB == null) {
            return false;
        }
        else return passwdInDB.equals(passwd);
    }

    // user register
    public String register(Integer id, String passwd, String name, String greet) {
        if (userMapper.getUserById(id) != null) {
            return "User already exists";
        }
        else {
            User user = new User(id, passwd, name, false, greet);
            userMapper.insertUser(user);
            return "Register successfully";
        }
    }

}
