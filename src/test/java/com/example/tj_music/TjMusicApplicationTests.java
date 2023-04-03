package com.example.tj_music;

import com.example.tj_music.db.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.tj_music.db.entity.User;
import java.util.List;


@SpringBootTest
class TjMusicApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * Test for UserMapper
     */
    @Autowired
    private UserMapper userMapper;
    @ Test
    public void testSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.getAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setId(2222222);
        user.setName("dio");
        user.setPasswd("123456");
        user.setGreet("hello");
        userMapper.insertUser(user);
    }


}
