package com.example.tj_music;

import com.example.tj_music.service.informationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TjMusicInformationTests {

    @Autowired
    private informationService informationService;

    // test getInformationById
    @Test
    public void testGetInformationById() {
        System.out.println(("----- getInformationById method test ------"));
        //User
        System.out.println("----- User ------");
        com.example.tj_music.db.entity.User return_value = informationService.getInformationById(1);
        System.out.println(return_value);
    }
}
