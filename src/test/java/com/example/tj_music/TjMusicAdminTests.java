package com.example.tj_music;

import com.example.tj_music.controller.followController;
import com.example.tj_music.service.followService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TjMusicAdminTests {
    @Autowired
    private com.example.tj_music.service.followService followService;
    @Autowired
    private com.example.tj_music.service.workService workService;
    @Autowired
    private com.example.tj_music.service.workCommentService workCommentService;

    private com.example.tj_music.controller.followController followController;

    @Test
    void test_delete_work_by_id(){
        System.out.println("----- delete work by id ------");
        int workId = 55;
        workService.deleteWorkAndCommentById(workId);
    }
}
