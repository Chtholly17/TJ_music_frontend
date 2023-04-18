package com.example.tj_music.service;

import com.example.tj_music.db.entity.Appeal;
import com.example.tj_music.db.mapper.FollowMapper;
import com.example.tj_music.db.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tj_music.db.entity.User;
import java.util.Date;

import java.util.List;

@Service
public class informationService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * get information by id
     * @param studentId
     * @return
     */
    public User getInformationById(int studentId) {
        return userMapper.selectUserById(studentId);
    }

    /**
     * updateUserNickName
     * @param userId
     * @return
     */
    public void updateUserNickName(String nickName, Integer userId) {
        userMapper.updateUserNicknameById(nickName, userId);
    }

    /**
     * updateUserCollege
     * @param userId
     * @return
     */
    public void updateUserCollege(String college, Integer userId) {
        userMapper.updateUserCollegeById(college, userId);
    }

    /**
     * updateUserMajor
     * @param userId
     * @return
     */
    public void updateUserMajor(String major, Integer userId) {
        userMapper.updateUserMajorById(major, userId);
    }


    /**
     * updateUserArea1
     * @param userId
     * @return
     */
    public void updateUserArea1(String area1, Integer userId) {
        userMapper.updateUserArea1ById(area1, userId);
    }

    /**
     * updateUserArea2
     * @param userId
     * @return
     */
    public void updateUserArea2(String area2, Integer userId) {
        userMapper.updateUserArea2ById(area2, userId);
    }

    /**
     * updateUserBirthday
     * @param userId
     * @return
     */
    public void updateUserBirthday(Date birthday, Integer userId) {
        userMapper.updateUserBirthdayById(birthday, userId);
    }
    /**
     * updateUserGender
     * @param userId
     * @return
     */
    public void updateUserGender(String gender, Integer userId){
        userMapper.updateUserGenderById(gender, userId);
    }

    /**
     * updateUserSignature
     * @param userId
     * @return
     */
    public void updateUserSignature(String signature, Integer userId) {
        userMapper.updateUserSignatureById(signature, userId);
    }

    /**
     * updateUserPassword
     * @param studentNum
     * @return
     */
    public void updateUserPassword(String studentNum, String password) {
        userMapper.updateUserPasswordByStudentNumber(studentNum, password);
    }

    /**
     * updateUserProfileImage
     * @param studentNum
     * @return
     */
    public void updateUserProfileImage(String studentNum, String profileImage) {
        userMapper.updateUserProfileImageByStudentNumber(studentNum, profileImage);
    }
}
