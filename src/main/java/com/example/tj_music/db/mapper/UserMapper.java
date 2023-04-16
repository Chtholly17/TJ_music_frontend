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
<<<<<<< Updated upstream
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserById(Integer id);

    // Select passwd by id
    @Select("SELECT passwd FROM user WHERE id = #{id}")
    public String getPasswdById(Integer id);
=======
    @Select("select * from user where user_id=#{user_id}")
    public User selectUserById(Integer user_id);

    // update user status by id
    @Select("update user set user_status=#{user_status} where user_id=#{user_id}")
    public void updateUserStatusById(String user_status, Integer user_id);

>>>>>>> Stashed changes
}
