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
     * get information by student number
     * @param studentNum
     * @return
     */
    public User getInformationByStudentNumber(String studentNum) {
        return userMapper.selectUserByStudentNumber(studentNum);
    }

    /**
     * get information by student number
     * @param studentNum
     * @return
     */
    public void updateUserNickName(String nickname, String studentNum){
        userMapper.updateUserNicknameByStudentNumber(nickname, studentNum);
    }

    /**
     * updateUserCollege
     * @param studentNum
     * @return
     */
    public void updateUserCollege(String college, String studentNum) {
        userMapper.updateUserCollegeByStudentNumber(college, studentNum);
    }

    /**
     * updateUserMajor
     * @param studentNum
     * @return
     */
    public void updateUserMajor(String major, String studentNum) {
        userMapper.updateUserMajorByStudentNumber(major, studentNum);
    }

    /**
     * updateUserArea1
     * @param studentNum
     * @return
     */
    public void updateUserArea1(String area1, String studentNum) {
        userMapper.updateUserArea1ByStudentNumber(area1, studentNum);
    }

    /**
     * updateUserArea2
     * @param studentNum
     * @return
     */
    public void updateUserArea2(String area2, String studentNum) {
        userMapper.updateUserArea2ByStudentNumber(area2, studentNum);
    }

    /**
     * updateUserBirthday
     * @param studentNum
     * @return
     */
    public void updateUserBirthday(Date birthday, String studentNum) {
        userMapper.updateUserBirthdayByStudentNumber(birthday, studentNum);
    }

    /**
     * updateUserGender
     * @param studentNum
     * @return
     */
    public void updateUserGender(String gender, String studentNum){
        userMapper.updateUserGenderByStudentNumber(gender, studentNum);
    }

    /**
     * updateUserSignature
     * @param studentNum
     * @return
     */
    public void updateUserSignature(String signature, String studentNum) {
        userMapper.updateUserSignatureByStudentNumber(signature, studentNum);
    }

    /**
     * updateUserPassword
     * @param studentNum
     * @return
     */
    public void updateUserPassword(String password, String studentNum) {
        userMapper.updateUserPasswordByStudentNumber(password, studentNum);
    }

    /**
     * updateUserProfileImage
     * @param studentNum
     * @return
     */
    public void updateUserProfileImage(String profileImage, String studentNum) {
        userMapper.updateUserProfileImageByStudentNumber(profileImage, studentNum);
    }
}
