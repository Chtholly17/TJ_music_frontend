package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.mapper.FollowMapper;
import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.db.entity.User;

import java.util.List;

@Service
public class informationService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserMapper userMapper;

    //getInformationById
    public User getInformationById(int studentId) {
        return userMapper.selectUserById(studentId);
    }

    //updateUserName
    public void updateUserName(String studentNum, String userName) {
        userMapper.updateUserNameByStudentNumber(studentNum, userName);
    }

    //updateUserPassword
    public void updateUserPassword(String studentNum, String userPassword) {
        userMapper.updateUserPasswordByStudentNumber(studentNum, userPassword);
    }

    //updateUserSignature
    public void updateUserSignature(String studentNum, String userSignature) {
        userMapper.updateUserSignatureByStudentNumber(studentNum, userSignature);
    }

    //updateUserProfileImageByStudentNumber
    public void updateUserProfileImageByStudentNumber(String studentNum, String userProfileImage) {
        userMapper.updateUserProfileImageByStudentNumber(studentNum, userProfileImage);
    }
}
