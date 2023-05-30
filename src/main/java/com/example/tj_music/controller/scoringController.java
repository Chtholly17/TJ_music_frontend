package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Music;
import com.example.tj_music.db.mapper.UserMapper;
import com.example.tj_music.db.entity.User;
import com.example.tj_music.service.originService;
import com.example.tj_music.service.accountService;
import com.example.tj_music.utils.Result;
import com.example.tj_music.utils.MusicUtils;
import com.example.tj_music.service.scoringService;
import com.example.tj_music.service.workService;
import com.example.tj_music.db.entity.Origin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tj_music.utils.PythonUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private workService workService;
    @Autowired
    private accountService accountService;
    @Autowired
    private UserMapper userMapper;

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
     * @param music
     * @return
     */
    @PostMapping("/getScores")
    public Result getScores(Music music) throws IOException {
        MultipartFile file = music.getFile();
        Integer originId = music.getOriginId();
        String userStudentNumber = music.getUserStudentNumber();
        System.out.println("originId: " + originId);
        System.out.println("userStudentNumber: " + userStudentNumber);
        System.out.println("file: " + file);
        Origin origin = originService.getOriginByOriginId(originId);

        String[] parts = origin.getOriginVoiceFilename().split("/");
        String origin_name = parts[parts.length - 1];

        parts = origin.getOriginBgmusicFilename().split("/");
        String origin_bgm_name = parts[parts.length - 1];

        scoringService.saveTmpMp3(file, userStudentNumber);
        String url = "http://49.4.115.48:8888" + "/" + userStudentNumber +"/music/" + origin_name;


        String work_voice_path = "/root/TJ_music/static/" + userStudentNumber + "/music/vocal.wav";
        String origin_bgm_path = "/root/TJ_music/static/admin/music/" + origin_bgm_name;
        String outputPath = scoringService.mergeMp3(origin_bgm_path, work_voice_path, userStudentNumber,
                origin_name);
        System.out.println("origin_bgm_path: " + origin_bgm_path);
        System.out.println("work_voice_path: " + work_voice_path);
        System.out.println("outputPath: " + outputPath);
        Map<String, Object> map = new HashMap<>();
        map.put("scores", pythonUtils.getScore("/root/TJ_music/static/admin/music/" + origin_name, outputPath));
        map.put("url", url);
//        String work_voice_file_name = "http://49.4.115.48:8888/" + userStudentNumber + "/music/" + origin_name;
//        String preface_file_name = origin.getOriginPrefaceFilename();

//        // 从外部的 map 中获取 scores
//        Map<String, Object> scoresMap = (Map<String, Object>) map.get("scores");
//
//        // 从 scoresMap 中获取 scoreList
//        Map<String, String> scoreList = (Map<String, String>) scoresMap.get("scoreList");
//
//        User user = userMapper.selectUserByStudentNumber(userStudentNumber);
//        workService.insertWork(
//                origin.getOriginName(),
//                null,
//                user.getUserId(),
//                originId,
//                0,
//                work_voice_file_name,
//                null,
//                preface_file_name,
//                Integer.parseInt(scoreList.get("preciseScore")),
//                Integer.parseInt(scoreList.get("qualityScore")),
//                Integer.parseInt(scoreList.get("pitchScore"))
//        );
        return Result.success(map);
    }

}