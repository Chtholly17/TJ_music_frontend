package com.example.tj_music.db.mapper;

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
    @Select("INSERT INTO user (id, passwd, name, banned, greet) "
            + "VALUES (#{id}, #{passwd}, #{name},False, #{greet})")
    public void insertUser(User user);

    // select user by id
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserById(Integer id);

    // Select passwd by id
    @Select("SELECT passwd FROM user WHERE id = #{id}")
    public String getPasswdById(Integer id);
}
