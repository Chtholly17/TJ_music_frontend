package com.example.tj_music.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.tj_music.db.entity.User;
import java.util.List;
@Mapper
public interface UserMapper {
    // Select all users
    @Select("select * from user")
    public List<User> getAllUser();

    // register
    @Insert("insert into user(user_student_number,user_nickname,user_password) values(#{user_student_number},#{user_nickname},#{user_password})")
    public int register(String user_student_number, String user_nickname, String user_password);
}
