package com.example.tj_music;

import com.example.tj_music.db.entity.User;
import com.example.tj_music.db.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TjMusicUserTests {
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

    @Test
    public void testSelectUserByStudentNumber() {
        System.out.println(("----- selectUserByStudentNumber method test ------"));
        //User
        System.out.println("----- User ------");
        User user = userMapper.getUserByStudentNumber("20170101");
        System.out.println(user);
    }
}
