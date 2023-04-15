package com.example.tj_music;

import com.example.tj_music.db.entity.Follow;
import com.example.tj_music.db.entity.Origin;
import com.example.tj_music.db.entity.Work;
import com.example.tj_music.db.mapper.FollowMapper;
import com.example.tj_music.db.mapper.OriginMapper;
import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.db.mapper.WorkMapper;
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
    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private OriginMapper originMapper;

    @ Test
    public void testSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.getAll();
        userList.forEach(System.out::println);
    }

    /**
     * Test for register user
     */
    @ Test
    public void testRegister() {
        System.out.println(("----- register method test ------"));
        userMapper.register("77777","test","123456");
    }

    /**
     * Test get user by student number
     */
    @ Test
    public void testGetUserByStudentNumber() {
        System.out.println(("----- getUserByStudentNumber method test ------"));
        User user = userMapper.getUserByStudentNumber("2053195");
        if(user==null){
            System.out.println("null object");
        }else {
            System.out.println(user);
        }
    }

    /**
     * Test get user by id
     */
    @Test
    public void testGetUserById() {
        System.out.println(("----- getUserByID method test ------"));
        User user = userMapper.getUserById(1);
        if(user==null){
            System.out.println("null object");
        }else{
            System.out.println(user);
        }
    }

    /**
     * Test get work by id
     */
    @Test
    public void testGetWorkById(){
        System.out.println(("----- getWorkById method test ------"));
        Work work = workMapper.getWorkById(1);
        if(work==null){
            System.out.println("null object");
        }else{
            System.out.println(work);
        }
    }

    /**
     * Test get follow
     */
    @Test
    public void testGetFollow(){
        System.out.println(("----- getFollow method test ------"));
        List<Follow> followList = followMapper.getAll();
        followList.forEach(System.out::println);
    }

    /**
     * Test get origin
     */
    @Test
    public void testGetOrigin(){
        System.out.println(("----- getOrigin method test ------"));
        List<Origin> workList = originMapper.getAll();
        workList.forEach(System.out::println);
    }
}

