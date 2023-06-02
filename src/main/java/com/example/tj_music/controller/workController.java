package com.example.tj_music.controller;

import com.example.tj_music.service.originService;
import com.example.tj_music.service.workService;
import com.example.tj_music.utils.Result;
import com.example.tj_music.db.entity.Origin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class workController {
    private static Logger log = Logger.getLogger("UserController.class");

    @Autowired
    private workService workService;
    @Autowired
    private originService originService;

    /**
     * get related works by origin id.Use for origin detail page
     * @param originId: origin id
     * @return Result, data is a list of work can be null.
     */
    @GetMapping("/relatedWork")
    public Result selectRelatedWorks(@RequestParam("originId") int originId) {
        return workService.selectWorkByOriginId(originId);
    }

    /**
     * get work in main page, with highest n like numbers.
     * @param workNumber: work number
     * @return Result, data is a list of work, length is workNumber.
     */
    @GetMapping("/mainPageWorks")
    public Result getMainPageWorks(@RequestParam("workNumber") int workNumber) {
        return workService.getNWorks(workNumber);
    }


    /**
     * 通过tag搜索作品，同时按照order排序
     * tag的可选项: “民谣” "摇滚" "金属" "古典" "电子" "热歌" “新歌” "飙升"
     * order的可选项: "like" "comment" "fans"
     * @param tag
     * @param order
     * @return
     */
    @GetMapping("/getWorkList")
    public Result getWorkList(@RequestParam("tag") String tag, @RequestParam("order") String order) {
        return workService.getWorkList(tag, order);
    }


    /**
     * 通过workId获取作品
     * @param workId 作品id
     * @return
     */
    @GetMapping("/getWorkById")
    public Result getWorkById(@RequestParam("workId") int workId) {
        return workService.getWorkById(workId);
    }

    /**
     * Insert work
     * @param workName
     * @param workComment
     * @param userStudentNumber
     * @param workOriginVersion
     * @param workVoiceFilename
     * @param workPreciseScore
     * @param workQualityScore
     * @param workPitchScore
     * @return
     */
    @PostMapping("/insertWork")
    public Result insertWork(@RequestParam("workName") String workName, @RequestParam("workComment") String workComment, @RequestParam("workOwner") String workOwner, @RequestParam("workOriginVersion") int workOriginVersion, @RequestParam("workVoiceFilename") String workVoiceFilename, @RequestParam("workPreciseScore") int workPreciseScore, @RequestParam("workQualityScore") int workQualityScore, @RequestParam("workPitchScore") int workPitchScore) {
        String workPrefaceFilename;
        Origin origin = originService.getOriginByOriginId(workOriginVersion);
        if (origin == null) {
            return Result.fail("origin does not exist.");
        }
        return workService.insertWork(
                workName,
                workComment,
                workOwner,
                workOriginVersion,
                0,
                workVoiceFilename,
                origin.getOriginTag(),
                origin.getOriginPrefaceFilename(),
                workPreciseScore,
                workQualityScore,
                workPitchScore
        );
    }

    /**
     * 通过workId给作品点赞
     * 需要提供workId
     * @param workId 作品id
     * @return 成功返回success，失败返回fail(code:0)
     */
    @PostMapping("/addLikeToWork")
    public Result addLikeToWork(@RequestParam("workId") int workId) {
        return workService.addLikeToWork(workId);
    }

}
