package com.example.tj_music.controller;

import com.example.tj_music.service.originService;
import com.example.tj_music.db.mapper.OriginMapper;
import com.example.tj_music.utils.Result;
import com.example.tj_music.utils.MusicUtils;
import com.example.tj_music.service.scoringService;
import com.example.tj_music.db.entity.Origin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tj_music.utils.PythonUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController // @RestController = @Controller + @ResponseBody (return json)
public class scoringController {
    // please use the logger to print the log
    private static Logger log = Logger.getLogger("UserController.class");
    @Autowired // auto-inject
    private PythonUtils pythonUtils;
    // user service
    @Autowired // auto-inject
    private scoringService scoringService;
    @Autowired
    private originService originService;

    /**
     * get comments by scores
     * @param preciseScore
     * @param qualityScore
     * @param pitchScore
     * @return
     */
    @PostMapping("/getComments")
    public Result getComments(@RequestParam("preciseScore") String preciseScore, @RequestParam("qualityScore") String qualityScore, @RequestParam("pitchScore") String pitchScore) {
        return pythonUtils.getComments(preciseScore, qualityScore, pitchScore);
    }

    /**
     * get scores by origin id and user id
     * @param file
     * @param originId
     * @param userStudentNumber
     * @return
     */
    @PostMapping("/getScores")
    public Result getScores(@RequestParam("file") MultipartFile file, @RequestParam("originId") Integer originId,
                            @RequestParam("userStudentNumber") String userStudentNumber) throws IOException {
        scoringService.saveTmpMp3(file, userStudentNumber);
        Origin origin = originService.getOriginByOriginId(originId);
        String work_voice_path = "/root/TJ_music/static/" + userStudentNumber + "/music/vocal.mp3";
        String origin_bgm_path = "/root/TJ_music/static/admin/" + origin.getOriginName() + "_bgmusic.mp3";
        String outputPath = scoringService.mergeMp3(origin_bgm_path, work_voice_path, userStudentNumber,
                origin.getOriginName());
        System.out.println("origin_bgm_path: " + origin_bgm_path);
        System.out.println("work_voice_path: " + work_voice_path);
        System.out.println("outputPath: " + outputPath);

        return pythonUtils.getScore("/root/TJ_music/static/admin/" + origin.getOriginName() + ".mp3",
                outputPath);
    }

}