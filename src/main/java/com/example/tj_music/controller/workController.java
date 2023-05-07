package com.example.tj_music.controller;

import com.example.tj_music.db.entity.Work;
import com.example.tj_music.service.workService;
import com.example.tj_music.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class workController {
    private static Logger log = Logger.getLogger("UserController.class");

    @Autowired
    private workService workService;

    /**
     * get work in main page, with highest n like numbers.
     * @param workNumber
     * @return Result, data is a list of work, length is workNumber.
     */
    @GetMapping("/mainPageWorks")
    public Result getMainPageWorks(@RequestParam("workNumber") int workNumber) {
        return workService.getNWorks(workNumber);
    }

    /**
     * get related works by origin id.
     * @param originId
     * @return Result, data is a list of work.
     */
    @GetMapping("/relatedWorks")
    public Result getRelatedWorks(@RequestParam("originId") int originId) {
        return workService.selectWorkByOriginId(originId);
    }

}
