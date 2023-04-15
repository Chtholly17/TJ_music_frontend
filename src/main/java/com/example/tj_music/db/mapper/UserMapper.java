package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.User;
import java.util.List;
@Mapper
public interface UserMapper {
    // Select all users
    @Select("SELECT * FROM user")
    public List<User> getAll();

    // insert a new user
    @Insert("INSERT INTO user(user_student_number,user_nickname,user_password) VALUES(#{user_student_number},#{user_nickname},#{user_password})")
    public void register(String user_student_number,String user_nickname,String user_password);

    // select user by student number
    @Select("SELECT * FROM user WHERE user_student_number = #{user_student_number}")
    User getUserByStudentNumber(String user_student_number);

    // select user by id
    @Select("SELECT * FROM user WHERE user_id = #{user_id}")
    User getUserById(int user_id);
}
