package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.User;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Date;
@Mapper
public interface UserMapper {
    // insert user
    @Insert("insert into user(user_student_number, user_password, user_nickname, user_signature, user_profile_image_filename) values(#{user_student_number}, #{user_password}, #{user_nickname}, #{user_signature}, #{user_profile_image_filename})")
    public void insertUser(String user_student_number, String user_password, String user_nickname, String user_signature, String user_profile_image_filename);

    // select user by student number
    @Select("select * from user where user_student_number=#{user_student_number}")
    public User selectUserByStudentNumber(String user_student_number);

    // select id by student number
    @Select("select user_id from user where user_student_number=#{user_student_number}")
    public Integer selectIdByStudentNumber(String user_student_number);

    // select user by id
    @Select("select * from user where user_id=#{user_id}")
    public User selectUserById(Integer user_id);

    // update user status by id
    @Update("update user set user_status=#{user_status} where user_id=#{user_id}")
    public void updateUserStatusById(String user_status, Integer user_id);

    // update user password by StudentNumber
    @Update("update user set user_password=#{user_password} where user_student_number=#{user_student_number}")
    public void updateUserPasswordByStudentNumber(String user_password, String user_student_number);

    // update user nickname by StudentNumber
    @Update("update user set user_nickname=#{user_nickname} where user_student_number=#{user_student_number}")
    public void updateUserNicknameByStudentNumber(String user_nickname, String user_student_number);

    // update user college by StudentNumber
    @Update("update user set user_college=#{user_college} where user_student_number=#{user_student_number}")
    public void updateUserCollegeByStudentNumber(String user_college, String user_student_number);

    // update user major by StudentNumber
    @Update("update user set user_major=#{user_major} where user_student_number=#{user_student_number}")
    public void updateUserMajorByStudentNumber(String user_major, String user_student_number);

    // update user area1 by StudentNumber
    @Update("update user set user_area1=#{user_area1} where user_student_number=#{user_student_number}")
    public void updateUserArea1ByStudentNumber(String user_area1, String user_student_number);

    // update user area2 by StudentNumber
    @Update("update user set user_area2=#{user_area2} where user_student_number=#{user_student_number}")
    public void updateUserArea2ByStudentNumber(String user_area2, String user_student_number);

    // update user birthday by StudentNumber
    @Update("update user set user_birthday=#{user_birthday} where user_student_number=#{user_student_number}")
    public void updateUserBirthdayByStudentNumber(Date user_birthday, String user_student_number);

    // update user gender by StudentNumber
    @Update("update user set user_gender=#{user_gender} where user_student_number=#{user_student_number}")
    public void updateUserGenderByStudentNumber(String user_gender, String user_student_number);

    // update user signature by StudentNumber
    @Update("update user set user_signature=#{user_signature} where user_student_number=#{user_student_number}")
    public void updateUserSignatureByStudentNumber(String user_signature, String user_student_number);

    // update user profile image file name by StudentNumber
    @Update("update user set user_profile_image_filename=#{user_profile_image} where user_student_number=#{user_student_number}")
    public void updateUserProfileImageByStudentNumber(String user_profile_image, String user_student_number);

    // update the user follow cnt by id
    @Update("update user set user_follow_cnt=#{user_follow_cnt} where user_id=#{user_id}")
    public void updateUserFollowCntById(Integer user_follow_cnt, Integer user_id);

    // update the user fans cnt by id
    @Update("update user set user_fans_cnt=#{user_fans_cnt} where user_id=#{user_id}")
    public void updateUserFansCntById(Integer user_fans_cnt, Integer user_id);

    // update the user post cnt by id
    @Update("update user set user_posts_cnt=#{user_post_cnt} where user_id=#{user_id}")
    public void updateUserPostsCntById(Integer user_posts_cnt, Integer user_id);
}
