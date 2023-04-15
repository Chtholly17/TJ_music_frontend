package com.example.tj_music;

import com.example.tj_music.db.entity.*;
import com.example.tj_music.db.mapper.*;
import com.example.tj_music.service.UserService;
import com.example.tj_music.service.WorkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private AppealMapper appealMapper;
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private OriginMapper originMapper;
    @Autowired
    private PostCommentMapper postCommentMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserService userService;

    @ Test
    public void testSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        //Appeal
        System.out.println("----- Appeal ------");
        List<Appeal> appealList = appealMapper.getAllAppeal();
        appealList.forEach(System.out::println);
        //Follow
        System.out.println("----- Follow ------");
        List<Follow> followList = followMapper.getAllFollow();
        followList.forEach(System.out::println);
        //Origin
        System.out.println("----- Origin ------");
        List<Origin> originList = originMapper.getAllOrigin();
        originList.forEach(System.out::println);
        //PostComment
        System.out.println("----- PostComment ------");
        List<PostComment> postCommentList = postCommentMapper.getAllPostComment();
        postCommentList.forEach(System.out::println);
        //Post
        System.out.println("----- Post ------");
        List<Post> postList = postMapper.getAllPost();
        postList.forEach(System.out::println);
        //User
        System.out.println("----- User ------");
        List<User> userList = userMapper.getAllUser();
        userList.forEach(System.out::println);
        //Work
        System.out.println("----- Work ------");
        List<Work> workList = workMapper.getAllWork();
        workList.forEach(System.out::println);
    }

    @ Test
    public void testRegister() {
        //register
        //user_student_number,user_nickname,user_password
        System.out.println("----- Register ------");
        int result = userService.register("2053635","rkr","123");
        System.out.println(result);
    }

    // test login
    @ Test
    public void testLogin() {
        System.out.println("----- Login ------");
        System.out.println("test1:successful login");
        boolean result = userService.login("20170101","test1");
        assert result==true;
        System.out.println("passed");

        System.out.println("test2:no such user");
        result = userService.login("123","test1");
        assert result==false;
        System.out.println("passed");

        System.out.println("test3:wrong password");
        result = userService.login("20170102","666");
        assert result==false;
        System.out.println("passed");


    }
}

