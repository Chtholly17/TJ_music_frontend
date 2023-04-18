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

    // update user Signature by StudentNumber
    @Update("update user set user_signature=#{user_signature} where user_student_number=#{user_student_number}")
    public void updateUserSignatureByStudentNumber(String user_signature, String user_student_number);

    // update user profile image file name by StudentNumber
    @Update("update user set user_profile_image_filename=#{user_profile_image} where user_student_number=#{user_student_number}")
    public void updateUserProfileImageByStudentNumber(String user_profile_image, String user_student_number);

    // update user name by StudentNumber
    @Update("update user set user_nickname=#{user_nickname} where user_student_number=#{user_student_number}")
    public void updateUserNameByStudentNumber(String user_nickname, String user_student_number);

    // select user by student number
    @Select("select * from user where user_student_number=#{user_student_number}")
    public User selectUserByStudentNumber(String user_student_number);

    // update user password by StudentNumber
    @Update("update user set user_password=#{user_password} where user_student_number=#{user_student_number}")
    public void updateUserPasswordByStudentNumber(String user_password, String user_student_number);

    // select user by id
    @Select("select * from user where user_id=#{user_id}")
    public User selectUserById(Integer user_id);

    // update user status by id
    @Update("update user set user_status=#{user_status} where user_id=#{user_id}")
    public void updateUserStatusById(String user_status, Integer user_id);

    // update user nickname by id
    @Update("update user set user_nickname=#{user_nickname} where user_id=#{user_id}")
    public void updateUserNicknameById(String user_nickname, Integer user_id);

    // update user college by id
    @Update("update user set user_college=#{user_college} where user_id=#{user_id}")
    public void updateUserCollegeById(String user_college, Integer user_id);

    // update user major by id
    @Update("update user set user_major=#{user_major} where user_id=#{user_id}")
    public void updateUserMajorById(String user_major, Integer user_id);

    // update user area1 by id
    @Update("update user set user_area1=#{user_area1} where user_id=#{user_id}")
    public void updateUserArea1ById(String user_area1, Integer user_id);

    // update user area2 by id
    @Update("update user set user_area2=#{user_area2} where user_id=#{user_id}")
    public void updateUserArea2ById(String user_area2, Integer user_id);

    // update user birthday by id
    @Update("update user set user_birthday=#{user_birthday} where user_id=#{user_id}")
    public void updateUserBirthdayById(Date user_birthday, Integer user_id);

    // update user gender by id
    @Update("update user set user_gender=#{user_gender} where user_id=#{user_id}")
    public void updateUserGenderById(String user_gender, Integer user_id);

    // update user signature by id
    @Update("update user set user_signature=#{user_signature} where user_id=#{user_id}")
    public void updateUserSignatureById(String user_signature, Integer user_id);

    // update user profile image file name by id
    @Update("update user set user_profile_image_filename=#{user_profile_image_filename} where user_id=#{user_id}")
    public void updateUserProfileImageById(String user_profile_image_filename, Integer user_id);


}
