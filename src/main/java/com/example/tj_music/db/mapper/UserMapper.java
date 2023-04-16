package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.User;
import java.util.List;
@Mapper
public interface UserMapper {
    // insert user
    @Insert("insert into user(user_student_number, user_password, user_nickname, user_signature, user_profile_image_filename) values(#{user_student_number}, #{user_password}, #{user_nickname}, #{user_signature}, #{user_profile_image_filename})")
    public void insertUser(String user_student_number, String user_password, String user_nickname, String user_signature, String user_profile_image_filename);

    // update user Signature by StudentNumber
    @Select("update user set user_signature=#{user_signature} where user_student_number=#{user_student_number}")
    public void updateUserSignatureByStudentNumber(String user_signature, String user_student_number);

    // update user profile image file name by StudentNumber
    @Select("update user set user_profile_image_filename=#{user_profile_image} where user_student_number=#{user_student_number}")
    public void updateUserProfileImageByStudentNumber(String user_profile_image, String user_student_number);

    // update user name by StudentNumber
    @Select("update user set user_nickname=#{user_nickname} where user_student_number=#{user_student_number}")
    public void updateUserNameByStudentNumber(String user_nickname, String user_student_number);

    // select user by student number
    @Select("select * from user where user_student_number=#{user_student_number}")
    public User selectUserByStudentNumber(String user_student_number);

    // update user password by StudentNumber
    @Select("update user set user_password=#{user_password} where user_student_number=#{user_student_number}")
    public void updateUserPasswordByStudentNumber(String user_password, String user_student_number);

    // select user by id
    @Select("select * from user where user_id=#{user_id}")
    public User selectUserById(Integer user_id);

    // update user status by id
    @Select("update user set user_status=#{user_status} where user_id=#{user_id}")
    public void updateUserStatusById(String user_status, Integer user_id);


}
