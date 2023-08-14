package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.*;
import com.example.tj_music.db.entity.User;

import java.util.List;
import java.util.Date;
@Mapper
public interface UserMapper {
    // insert user
    @Insert("insert into user(userStudentNumber, user_password, user_status, user_nickname, user_signature, user_profile_image_filename) values(#{userStudentNumber}, #{user_password}, #{user_status}, #{user_nickname}, #{user_signature}, #{user_profile_image_filename})")
    public void insertUser(String userStudentNumber, String user_password, String user_status, String user_nickname, String user_signature, String user_profile_image_filename);

    // get all users
    @Select("select * from user where user_status = 'normal' or user_status = 'banned'")
    public List<User> getAllUsers();

    // select user by student number
    @Select("select * from user where userStudentNumber=#{userStudentNumber}")
    public User selectUserByStudentNumber(String userStudentNumber);

    // select id by student number
    @Select("select userId from user where userStudentNumber=#{userStudentNumber}")
    public Integer selectIdByStudentNumber(String userStudentNumber);

    // select user by id
    @Select("select * from user where userId=#{userId}")
    public User selectUserById(Integer userId);

    // update user status by id
    @Update("update user set user_status=#{user_status} where userId=#{userId}")
    public void updateUserStatusById(String user_status, Integer userId);

    // update user password by StudentNumber
    @Update("update user set user_password=#{user_password} where userStudentNumber=#{userStudentNumber}")
    public void updateUserPasswordByStudentNumber(String user_password, String userStudentNumber);

    // update user verification code by StudentNumber
    @Update("update user set user_identifying_code=#{user_verification_code} where userStudentNumber=#{userStudentNumber}")
    public void updateUserVerificationCodeByStudentNumber(String user_verification_code, String userStudentNumber);

    // update user nickname by StudentNumber
    @Update("update user set user_nickname=#{user_nickname} where userStudentNumber=#{userStudentNumber}")
    public void updateUserNicknameByStudentNumber(String user_nickname, String userStudentNumber);

    // update user college by StudentNumber
    @Update("update user set user_college=#{user_college} where userStudentNumber=#{userStudentNumber}")
    public void updateUserCollegeByStudentNumber(String user_college, String userStudentNumber);

    // update user major by StudentNumber
    @Update("update user set user_major=#{user_major} where userStudentNumber=#{userStudentNumber}")
    public void updateUserMajorByStudentNumber(String user_major, String userStudentNumber);

    // update user area1 by StudentNumber
    @Update("update user set user_area1=#{user_area1} where userStudentNumber=#{userStudentNumber}")
    public void updateUserArea1ByStudentNumber(String user_area1, String userStudentNumber);

    // update user area2 by StudentNumber
    @Update("update user set user_area2=#{user_area2} where userStudentNumber=#{userStudentNumber}")
    public void updateUserArea2ByStudentNumber(String user_area2, String userStudentNumber);

    // update user birthday by StudentNumber
    @Update("update user set user_birthday=#{user_birthday} where userStudentNumber=#{userStudentNumber}")
    public void updateUserBirthdayByStudentNumber(Date user_birthday, String userStudentNumber);

    // update user gender by StudentNumber
    @Update("update user set user_gender=#{user_gender} where userStudentNumber=#{userStudentNumber}")
    public void updateUserGenderByStudentNumber(String user_gender, String userStudentNumber);

    // update user signature by StudentNumber
    @Update("update user set user_signature=#{user_signature} where userStudentNumber=#{userStudentNumber}")
    public void updateUserSignatureByStudentNumber(String user_signature, String userStudentNumber);

    // update user profile image file name by StudentNumber
    @Update("update user set user_profile_image_filename=#{user_profile_image} where userStudentNumber=#{userStudentNumber}")
    public void updateUserProfileImageByStudentNumber(String user_profile_image, String userStudentNumber);

    // update the user follow cnt by id
    @Update("update user set user_follow_cnt=#{user_follow_cnt} where userId=#{userId}")
    public void updateUserFollowCntById(Integer user_follow_cnt, Integer userId);

    // update the user fans cnt by id
    @Update("update user set user_fans_cnt=#{user_fans_cnt} where userId=#{userId}")
    public void updateUserFansCntById(Integer user_fans_cnt, Integer userId);

    // update the user post cnt by id
    @Update("update user set user_posts_cnt=#{user_post_cnt} where userId=#{userId}")
    public void updateUserPostsCntById(Integer user_posts_cnt, Integer userId);

    // get the fans cnt by id
    @Select("select count(*) from user_follow where user_followed_id=#{userId}")
    public Integer selectFansCntById(Integer userId);

    // delete the user by id
    @Delete("delete from user where userId=#{userId}")
    public void deleteUserById(Integer userId);

    // delete the user by student number
    @Delete("delete from user where userStudentNumber=#{userStudentNumber}")
    public void deleteUserByStudentNumber(String userStudentNumber);
}
